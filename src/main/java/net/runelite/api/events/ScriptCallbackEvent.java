/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
package net.runelite.api.events;

import net.runelite.api.Script;

/**
 * An event where a Runelite ASM script is called.
 */
public class ScriptCallbackEvent
{
	/**
	 * The script being called.
	 */
	private Script script;
	/**
	 * The name of the event that triggered script execution.
	 */
	private String eventName;

    public ScriptCallbackEvent() {
    }

    public Script getScript() {
        return this.script;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ScriptCallbackEvent)) return false;
        final ScriptCallbackEvent other = (ScriptCallbackEvent) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$script = this.getScript();
        final Object other$script = other.getScript();
        if (this$script == null ? other$script != null : !this$script.equals(other$script)) return false;
        final Object this$eventName = this.getEventName();
        final Object other$eventName = other.getEventName();
        if (this$eventName == null ? other$eventName != null : !this$eventName.equals(other$eventName)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ScriptCallbackEvent;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $script = this.getScript();
        result = result * PRIME + ($script == null ? 43 : $script.hashCode());
        final Object $eventName = this.getEventName();
        result = result * PRIME + ($eventName == null ? 43 : $eventName.hashCode());
        return result;
    }

    public String toString() {
        return "ScriptCallbackEvent(script=" + this.getScript() + ", eventName=" + this.getEventName() + ")";
    }
}
