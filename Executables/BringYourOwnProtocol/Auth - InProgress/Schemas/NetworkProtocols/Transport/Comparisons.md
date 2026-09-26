# Transport Protocol Comparison

| Schema | Delivery model | Strengths | Trade-offs and fit |
|---|---|---|---|
| [TCP](TCP.json) | Reliable, ordered byte stream | Broad support, congestion control, and delivery retransmission | Connection setup and ordered delivery can add latency; one lost segment can delay later bytes. |
| [UDP](UDP.json) | Unreliable datagrams | Low overhead and useful for real-time or application-managed transport | No built-in reliability, ordering, congestion control, or encryption; applications must provide what they need. |
| [SCTP](SCTP.json) | Reliable message transport with streams | Multi-streaming and multi-homing can reduce head-of-line blocking and improve resilience | Less ubiquitous than TCP/UDP and sometimes blocked by network middleboxes. |
| [QUIC](QUIC.json) | Secure multiplexed transport over UDP | Integrated TLS 1.3, stream multiplexing, connection migration, and lower setup latency | More complex implementation; UDP reachability and library support are required. |

Choose TCP for broad reliable-stream compatibility, UDP when the application manages delivery or latency is central, SCTP for message-oriented multi-streaming where supported, and QUIC for modern secure multiplexed connections.