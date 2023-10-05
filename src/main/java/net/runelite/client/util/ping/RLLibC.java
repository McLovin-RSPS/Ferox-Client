package net.runelite.client.util.ping;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.unix.LibC;

interface RLLibC extends LibC
{
    RLLibC INSTANCE = Native.loadLibrary(NAME, RLLibC.class);

    int AF_INET = 2;
    int SOCK_DGRAM = 2;
    int SOL_SOCKET = 1;
    int IPPROTO_ICMP = 1;
    int SO_RCVTIMEO = 20;

    int socket(int domain, int type, int protocol);

    int close(int socket);

    int sendto(int sockfd, byte[] buf, int len, int flags, byte[] dest_addr, int addrlen);

    int recvfrom(int sockfd, Pointer buf, int len, int flags, Pointer src_addr, Pointer addrlen);

    int setsockopt(int sockfd, int level, int optname, Pointer optval, int optlen);
}
