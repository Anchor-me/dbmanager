#!/bin/bash

curl -XPUT 'http://localhost:9200/goals' -d '{
    "settings": {
        "number_of_shards": 1
    },
    "mappings": {
        "goal": {
            "properties": {
                "id" : {
                    "type": "string",
                    "index": "analyzed"
                },
                "themeId" : {
                    "type" : "string",
                    "index": "analyzed"
                },
                "summary": {
                    "type": "string"
                },
                "description": {
                    "type": "string"
                },
                "level": {
                    "type": "integer"
                },
                "priority": {
                    "type": "boolean"
                }
            }
        }
    }
}'
