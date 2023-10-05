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
package net.runelite.api.events;

import net.runelite.api.GrandExchangeOffer;
import net.runelite.api.GrandExchangeOfferState;

/**
 * An event where a {@link GrandExchangeOffer} has been updated with
 * new information.
 * <p>
 * When the client initially logs in, this event is called for all grand
 * exchange slots with the {@link GrandExchangeOfferState#EMPTY} state,
 * regardless of whether any slots have offers. Once the exchange is
 * initialized, the client then updates any offers with items as it
 * receives information from the server.
 * <p>
 * See {@link GrandExchangeOfferState} for potential states an offer
 * can change into.
 */
public class GrandExchangeOfferChanged
{
	/**
	 * The offer that has been modified.
	 */
	private GrandExchangeOffer offer;
	/**
	 * The index value of the slot.
	 */
	private int slot;

    public GrandExchangeOfferChanged() {
    }

    public GrandExchangeOffer getOffer() {
        return this.offer;
    }

    public int getSlot() {
        return this.slot;
    }

    public void setOffer(GrandExchangeOffer offer) {
        this.offer = offer;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GrandExchangeOfferChanged)) return false;
        final GrandExchangeOfferChanged other = (GrandExchangeOfferChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$offer = this.getOffer();
        final Object other$offer = other.getOffer();
        if (this$offer == null ? other$offer != null : !this$offer.equals(other$offer)) return false;
        if (this.getSlot() != other.getSlot()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GrandExchangeOfferChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $offer = this.getOffer();
        result = result * PRIME + ($offer == null ? 43 : $offer.hashCode());
        result = result * PRIME + this.getSlot();
        return result;
    }

    public String toString() {
        return "GrandExchangeOfferChanged(offer=" + this.getOffer() + ", slot=" + this.getSlot() + ")";
    }
}
