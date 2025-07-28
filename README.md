# flight-Agrigators
Flight-agrigator-application

System Architecture Overview
<br/>
Client --> API Gateway --> AWS Lambda (Aggregator)
                                     |
                                     |---> FlightProviderAdapter1 (e.g., Amadeus)
                                     |---> FlightProviderAdapter2 (e.g., Sabre)
                                     |
                                  Canonical Model
