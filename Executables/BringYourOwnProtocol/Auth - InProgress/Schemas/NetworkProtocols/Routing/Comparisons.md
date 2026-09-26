# Routing Protocol Comparison

| Schema | Routing scope | Strengths | Trade-offs and fit |
|---|---|---|---|
| [BGP](BGP.json) | Between autonomous systems; also used for large internal routing domains | Policy-driven, highly scalable route exchange and the Internet's inter-domain routing foundation | Complex policy and route-leak risks; prefix filtering, maximum-prefix limits, and peer authentication are important. |
| [OSPF](OSPF.json) | Within an autonomous system | Link-state convergence and well-understood area-based scaling | Requires consistent area/interface design and more internal topology state than simple distance-vector protocols. |

BGP selects routes based heavily on policy and is the standard for inter-domain routing. OSPF computes routes from link-state topology and is commonly used for routing within an organization.