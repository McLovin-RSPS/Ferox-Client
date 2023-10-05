/*
 * Copyright (c) 2018, Woox <https://github.com/wooxsolo>
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

import net.runelite.api.GraphicsObject;

/**
 * An event where a new {@link GraphicsObject} has been created.
 */
public final class GraphicsObjectCreated
{
	/**
	 * The newly created graphics object.
	 */
	private final GraphicsObject graphicsObject;

    public GraphicsObjectCreated(GraphicsObject graphicsObject) {
        this.graphicsObject = graphicsObject;
    }

    public GraphicsObject getGraphicsObject() {
        return this.graphicsObject;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GraphicsObjectCreated)) return false;
        final GraphicsObjectCreated other = (GraphicsObjectCreated) o;
        final Object this$graphicsObject = this.getGraphicsObject();
        final Object other$graphicsObject = other.getGraphicsObject();
        if (this$graphicsObject == null ? other$graphicsObject != null : !this$graphicsObject.equals(other$graphicsObject))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $graphicsObject = this.getGraphicsObject();
        result = result * PRIME + ($graphicsObject == null ? 43 : $graphicsObject.hashCode());
        return result;
    }

    public String toString() {
        return "GraphicsObjectCreated(graphicsObject=" + this.getGraphicsObject() + ")";
    }
}
