# flight-Agrigators
Flight-agrigator-application

System Architecture Overview
<br/>
Client --> API Gateway --> AWS Lambda (Aggregator) <br/>
                                     |<br/>
                                     |---> FlightProviderAdapter1 (e.g., Amadeus)<br/>
                                     |---> FlightProviderAdapter2 (e.g., Sabre)<br/>
                                     |<br/>
                                  Canonical Model<br/>
