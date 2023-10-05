/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.api.model;

/**
 * Represents 3 vertices as a three-dimensional Triangle.
 */
public final class Triangle
{
	private final Vertex a;
	private final Vertex b;
	private final Vertex c;

    public Triangle(Vertex a, Vertex b, Vertex c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
	 * Rotates the triangle by the given orientation.
	 *
	 * @param orientation passed orientation
	 * @return new instance
	 */
	public Triangle rotate(int orientation)
	{
		return new Triangle(
			a.rotate(orientation),
			b.rotate(orientation),
			c.rotate(orientation)
		);
	}

    public Vertex getA() {
        return this.a;
    }

    public Vertex getB() {
        return this.b;
    }

    public Vertex getC() {
        return this.c;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Triangle)) return false;
        final Triangle other = (Triangle) o;
        final Object this$a = this.getA();
        final Object other$a = other.getA();
        if (this$a == null ? other$a != null : !this$a.equals(other$a)) return false;
        final Object this$b = this.getB();
        final Object other$b = other.getB();
        if (this$b == null ? other$b != null : !this$b.equals(other$b)) return false;
        final Object this$c = this.getC();
        final Object other$c = other.getC();
        if (this$c == null ? other$c != null : !this$c.equals(other$c)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $a = this.getA();
        result = result * PRIME + ($a == null ? 43 : $a.hashCode());
        final Object $b = this.getB();
        result = result * PRIME + ($b == null ? 43 : $b.hashCode());
        final Object $c = this.getC();
        result = result * PRIME + ($c == null ? 43 : $c.hashCode());
        return result;
    }

    public String toString() {
        return "Triangle(a=" + this.getA() + ", b=" + this.getB() + ", c=" + this.getC() + ")";
    }
}
