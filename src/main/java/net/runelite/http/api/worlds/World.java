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
package net.runelite.http.api.worlds;

import java.util.EnumSet;

public final class World
{
	private final int id;
	private final EnumSet<WorldType> types;
	private final String address;
	private final String activity;
	private final int location;
	private final int players;

    World(int id, EnumSet<WorldType> types, String address, String activity, int location, int players) {
        this.id = id;
        this.types = types;
        this.address = address;
        this.activity = activity;
        this.location = location;
        this.players = players;
    }

    public static WorldBuilder builder() {
        return new WorldBuilder();
    }

    public int getId() {
        return this.id;
    }

    public EnumSet<WorldType> getTypes() {
        return this.types;
    }

    public String getAddress() {
        return this.address;
    }

    public String getActivity() {
        return this.activity;
    }

    public int getLocation() {
        return this.location;
    }

    public int getPlayers() {
        return this.players;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof World)) return false;
        final World other = (World) o;
        if (this.getId() != other.getId()) return false;
        final Object this$types = this.getTypes();
        final Object other$types = other.getTypes();
        if (this$types == null ? other$types != null : !this$types.equals(other$types)) return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$activity = this.getActivity();
        final Object other$activity = other.getActivity();
        if (this$activity == null ? other$activity != null : !this$activity.equals(other$activity)) return false;
        if (this.getLocation() != other.getLocation()) return false;
        if (this.getPlayers() != other.getPlayers()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $types = this.getTypes();
        result = result * PRIME + ($types == null ? 43 : $types.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $activity = this.getActivity();
        result = result * PRIME + ($activity == null ? 43 : $activity.hashCode());
        result = result * PRIME + this.getLocation();
        result = result * PRIME + this.getPlayers();
        return result;
    }

    public String toString() {
        return "World(id=" + this.getId() + ", types=" + this.getTypes() + ", address=" + this.getAddress() + ", activity=" + this.getActivity() + ", location=" + this.getLocation() + ", players=" + this.getPlayers() + ")";
    }

    public static class WorldBuilder {
        private int id;
        private EnumSet<WorldType> types;
        private String address;
        private String activity;
        private int location;
        private int players;

        WorldBuilder() {
        }

        public WorldBuilder id(int id) {
            this.id = id;
            return this;
        }

        public WorldBuilder types(EnumSet<WorldType> types) {
            this.types = types;
            return this;
        }

        public WorldBuilder address(String address) {
            this.address = address;
            return this;
        }

        public WorldBuilder activity(String activity) {
            this.activity = activity;
            return this;
        }

        public WorldBuilder location(int location) {
            this.location = location;
            return this;
        }

        public WorldBuilder players(int players) {
            this.players = players;
            return this;
        }

        public World build() {
            return new World(id, types, address, activity, location, players);
        }

        public String toString() {
            return "World.WorldBuilder(id=" + this.id + ", types=" + this.types + ", address=" + this.address + ", activity=" + this.activity + ", location=" + this.location + ", players=" + this.players + ")";
        }
    }
}
