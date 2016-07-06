#!/bin/bash

curl -XPUT 'http://localhost:9200/examples' -d '{
    "settings": {
        "number_of_shards": 1
    },
    "mappings": {
        "example": {
            "properties": {
                "id" : {
                    "type": "string",
                    "index": "not_analyzed"
                },
                "name" : {
                    "type": "string",
                    "index": "analyzed",
                    "fields": {
                        "raw" : {
                            "type": "string",
                            "index": "not_analyzed"
                        }
                    }
                }
            }
        }
    }
}'