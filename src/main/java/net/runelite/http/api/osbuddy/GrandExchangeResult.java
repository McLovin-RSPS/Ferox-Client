/*
 * Copyright (c) 2018, AeonLucid <https://github.com/AeonLucid>
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
package net.runelite.http.api.osbuddy;

import java.time.Instant;

public class GrandExchangeResult
{
	private int item_id;
	private int buy_average;
	private int sell_average;
	private int overall_average;
	private Instant last_update;

    public GrandExchangeResult() {
    }

    public int getItem_id() {
        return this.item_id;
    }

    public int getBuy_average() {
        return this.buy_average;
    }

    public int getSell_average() {
        return this.sell_average;
    }

    public int getOverall_average() {
        return this.overall_average;
    }

    public Instant getLast_update() {
        return this.last_update;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setBuy_average(int buy_average) {
        this.buy_average = buy_average;
    }

    public void setSell_average(int sell_average) {
        this.sell_average = sell_average;
    }

    public void setOverall_average(int overall_average) {
        this.overall_average = overall_average;
    }

    public void setLast_update(Instant last_update) {
        this.last_update = last_update;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GrandExchangeResult)) return false;
        final GrandExchangeResult other = (GrandExchangeResult) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getItem_id() != other.getItem_id()) return false;
        if (this.getBuy_average() != other.getBuy_average()) return false;
        if (this.getSell_average() != other.getSell_average()) return false;
        if (this.getOverall_average() != other.getOverall_average()) return false;
        final Object this$last_update = this.getLast_update();
        final Object other$last_update = other.getLast_update();
        if (this$last_update == null ? other$last_update != null : !this$last_update.equals(other$last_update))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GrandExchangeResult;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getItem_id();
        result = result * PRIME + this.getBuy_average();
        result = result * PRIME + this.getSell_average();
        result = result * PRIME + this.getOverall_average();
        final Object $last_update = this.getLast_update();
        result = result * PRIME + ($last_update == null ? 43 : $last_update.hashCode());
        return result;
    }

    public String toString() {
        return "GrandExchangeResult(item_id=" + this.getItem_id() + ", buy_average=" + this.getBuy_average() + ", sell_average=" + this.getSell_average() + ", overall_average=" + this.getOverall_average() + ", last_update=" + this.getLast_update() + ")";
    }
}
