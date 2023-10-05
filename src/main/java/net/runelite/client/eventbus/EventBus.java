/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
 * Copyright (c) 2018, Abex
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.runelite.client.RuneLite;
import net.runelite.client.util.ReflectUtil;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;
import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

@ThreadSafe
public class EventBus
{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(EventBus.class);
    @Inject
	private ScheduledExecutorService executor;

    public EventBus(Consumer<Throwable> exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @FunctionalInterface
	public interface SubscriberMethod
	{
		void invoke(Object event);
	}

	private static final class Subscriber
	{
		private final Object object;
		private final Method method;
		private final float priority;
		private final SubscriberMethod lamda;

        public Subscriber(Object object, Method method, float priority, SubscriberMethod lamda) {
            this.object = object;
            this.method = method;
            this.priority = priority;
            this.lamda = lamda;
        }

        void invoke(final Object arg) throws Exception
		{
			if (lamda != null)
			{
				lamda.invoke(arg);
			}
			else
			{
				method.invoke(object, arg);
			}
		}

        public Object getObject() {
            return this.object;
        }

        public Method getMethod() {
            return this.method;
        }

        public float getPriority() {
            return this.priority;
        }

        public SubscriberMethod getLamda() {
            return this.lamda;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Subscriber)) return false;
            final Subscriber other = (Subscriber) o;
            final Object this$object = this.getObject();
            final Object other$object = other.getObject();
            if (this$object == null ? other$object != null : !this$object.equals(other$object)) return false;
            final Object this$method = this.getMethod();
            final Object other$method = other.getMethod();
            if (this$method == null ? other$method != null : !this$method.equals(other$method)) return false;
            if (Float.compare(this.getPriority(), other.getPriority()) != 0) return false;
            final Object this$lamda = this.getLamda();
            final Object other$lamda = other.getLamda();
            if (this$lamda == null ? other$lamda != null : !this$lamda.equals(other$lamda)) return false;
            return true;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $object = this.getObject();
            result = result * PRIME + ($object == null ? 43 : $object.hashCode());
            final Object $method = this.getMethod();
            result = result * PRIME + ($method == null ? 43 : $method.hashCode());
            result = result * PRIME + Float.floatToIntBits(this.getPriority());
            final Object $lamda = this.getLamda();
            result = result * PRIME + ($lamda == null ? 43 : $lamda.hashCode());
            return result;
        }

        public String toString() {
            return "EventBus.Subscriber(object=" + this.getObject() + ", method=" + this.getMethod() + ", priority=" + this.getPriority() + ", lamda=" + this.getLamda() + ")";
        }
    }

	private final Consumer<Throwable> exceptionHandler;
	private ImmutableMultimap<Class, Subscriber> subscribers = ImmutableMultimap.of();

	/**
	 * Instantiates EventBus with default exception handler
	 */
	public EventBus()
	{
		this((e) -> log.warn("Uncaught exception in event subscriber", e));
	}

	/**
	 * Registers subscriber to EventBus. All methods in subscriber and it's parent classes are checked for
	 * {@link Subscribe} annotation and then added to map of subscriptions.
	 *
	 * @param object subscriber to register
	 * @throws IllegalArgumentException in case subscriber method name is wrong (correct format is 'on' + EventName
	 */
	public synchronized void register(@Nonnull final Object object)
	{
		final ImmutableMultimap.Builder<Class, Subscriber> builder = ImmutableMultimap.builder();

		if (subscribers != null)
		{
			builder.putAll(subscribers);
		}

		builder.orderValuesBy(Comparator.comparing(Subscriber::getPriority).reversed()
			.thenComparing(s -> s.object.getClass().getName()));

		for (Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass())
		{
			for (final Method method : clazz.getDeclaredMethods())
			{
				final Subscribe sub = method.getAnnotation(Subscribe.class);

				if (sub == null)
				{
					continue;
				}

				Preconditions.checkArgument(method.getReturnType() == Void.TYPE, "@Subscribed method \"" + method + "\" cannot return a value");
				Preconditions.checkArgument(method.getParameterCount() == 1, "@Subscribed method \"" + method + "\" must take exactly 1 argument");
				Preconditions.checkArgument(!Modifier.isStatic(method.getModifiers()), "@Subscribed method \"" + method + "\" cannot be static");

				final Class<?> parameterClazz = method.getParameterTypes()[0];

				Preconditions.checkArgument(!parameterClazz.isPrimitive(), "@Subscribed method \"" + method + "\" cannot subscribe to primitives");
				Preconditions.checkArgument((parameterClazz.getModifiers() & (Modifier.ABSTRACT | Modifier.INTERFACE)) == 0, "@Subscribed method \"" + method + "\" cannot subscribe to polymorphic classes");

				for (Class<?> psc = parameterClazz.getSuperclass(); psc != null; psc = psc.getSuperclass())
				{
					if (subscribers.containsKey(psc))
					{
						throw new IllegalArgumentException("@Subscribed method \"" + method + "\" cannot subscribe to class which inherits from subscribed class \"" + psc + "\"");
					}
				}

				method.setAccessible(true);
				SubscriberMethod lambda = null;

				try
				{
					final MethodHandles.Lookup caller = ReflectUtil.privateLookupIn(clazz);
					final MethodType subscription = MethodType.methodType(void.class, parameterClazz);
					final MethodHandle target = caller.findVirtual(clazz, method.getName(), subscription);
					final CallSite site = LambdaMetafactory.metafactory(
						caller,
						"invoke",
						MethodType.methodType(SubscriberMethod.class, clazz),
						subscription.changeParameterType(0, Object.class),
						target,
						subscription);

					final MethodHandle factory = site.getTarget();
					lambda = (SubscriberMethod) factory.bindTo(object).invokeExact();
				}
				catch (Throwable e)
				{
					log.warn("Unable to create lambda for method {}", method, e);
				}

				final Subscriber subscriber = new Subscriber(object, method, sub.priority(), lambda);
				builder.put(parameterClazz, subscriber);
				log.debug("Registering {} - {}", parameterClazz, subscriber);
			}
		}

		subscribers = builder.build();
	}

	/**
	 * Unregisters all subscribed methods from provided subscriber object.
	 *
	 * @param object object to unsubscribe from
	 */
	public synchronized void unregister(@Nonnull final Object object)
	{
		if (subscribers == null)
		{
			return;
		}

		final Multimap<Class, Subscriber> map = HashMultimap.create();
		map.putAll(subscribers);

		for (Class<?> clazz = object.getClass(); clazz != null; clazz = clazz.getSuperclass())
		{
			for (final Method method : clazz.getDeclaredMethods())
			{
				final Subscribe sub = method.getAnnotation(Subscribe.class);

				if (sub == null)
				{
					continue;
				}

				final Class<?> parameterClazz = method.getParameterTypes()[0];
				map.remove(parameterClazz, new Subscriber(object, method, sub.priority(), null));
			}
		}

		subscribers = ImmutableMultimap.copyOf(map);
	}

	/**
	 * Posts provided event to all registered subscribers. Subscriber calls are invoked immediately,
	 * ordered by priority then their declaring class' name.
	 *
	 * @param event event to post
	 */
	public void post(@Nonnull final Object event)
	{
		RuneLite.getInjector().getInstance(ScheduledExecutorService.class).submit(() -> {
			for (final Subscriber subscriber : subscribers.get(event.getClass()))
			{
				try
				{
					subscriber.invoke(event);
				}
				catch (Exception e)
				{
					exceptionHandler.accept(e);
				}
			}
		});
	}
}
