# Hello Space Server

Example webhook server that can receive webhook requests from Somewear. Webhook requests are sent as http POST requests with a JSON payload.

## Example JSON Payloads

### Location

``` json
{
  "requestId": "66ef6f9b-8870-4e18-99e1-f14ee7eae91d",
  "payloads": [
    {
      "identity": {
        "id": "0",
        "name": "Example User",
        "type": "User",
        "email": "example@example.com"
      },
      "account": {
        "id": "0",
        "workspaceId": "0",
      },
      "workspace": {
        "id": "0",
        "name": "Example Workspace"
      },
      "events": [
        {
          "type": "Location",
          "latitude": "37.781001",
          "longitude": "-122.393456",
          "altitude": "1000.0",
          "timestamp": "2023-04-12T19:15:14Z"
        }
      ]
    }
  ]
}
```

### Waypoint
``` json
{
  "requestId": "66ef6f9b-8870-4e18-99e1-f14ee7eae91d",
  "payloads": [
    {
      ...
      "events": [
        {
          "type": "Waypoint",
          "latitude": "37.781001",
          "longitude": "-122.393456",
          "timestamp": "2023-04-12T19:15:14Z"
          "name": "Example Waypoint",
          "notes": "Example notes"
        }
      ]
    }
  ]
}
```

### Health Activity

```json
{
  "requestId": "e37f3184-c6b0-4f66-9b91-6304a98f1a07",
  "payloads": [
    {
      "identity": {
        "id": "0",
        "name": "Example User",
        "type": "User",
        "email": "example@example.com"
      },
      "account": {
        "id": "3",
        "workspaceId": "2"
      },
      "workspace": {
        "id": "2",
        "name": "ExampleWorkspace"
      },
      "events": [
        {
          "type": "HealthActivity",
          "timestamp": "2023-10-17T18:59:46Z",
          "heartRate": 72,
          "heartRateConfidence": 94,
          "breathingRate": 12.5,
          "breathingRateConfidence": 87,
          "posture": 12,
          "coreTemperature": 26.9
        }
      ]
    }
  ]
}
```

### Message
``` json
{
  "requestId": "66ef6f9b-8870-4e18-99e1-f14ee7eae91d",
  "payloads": [
    {
      ...
      "events": [
        {
          "type": "Message",
          "timestamp": "2023-04-12T19:15:14Z"
          "content": "Example content"
        }
      ]
    }
  ]
}
```


### Data
Data payloads are raw bytes that you can send through Somewear. You should only use this type if as a last resort since you won't get any Somewear platform benefits for free. For example, if you're sending a coordinate and you use the Location type, the location will automatically show up on the Somewear map on the Android, iOS, web, and ATAK clients.

``` json
{
  "requestId": "fe06b693-af2e-49da-963b-97fddaaa97eb",
  "payloads": [
    {
      "identity": {
        "id": "0",
        "name": "Example User",
        "type": "User",
        "email": "example@example.com"
      },
      "account": {
        "id": "0",
        "workspaceId": "0",
      },
      "events": [
        {
          "type": "Data",
          "payload": "VGVzdAo=",
          "timestamp": "2023-04-12T18:56:32Z"
        }
      ]
    }
  ]
}
```


