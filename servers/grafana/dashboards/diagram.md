graph LR
collaboration-tool --> message-bus
dynamic-case(dynamic-case) --> message-bus
collaboration-transformer-app(collaboration-transformer-app) --> message-bus
collaboration-transformer-app --> redis
task-command-consumer-app(task-command-consumer-app) --> message-bus
notification-command-consumer-app(notification-command-consumer-app) --> message-bus
message-bus --> task-indexer-app(task-indexer-app)
message-bus --> notification-indexer-app(notification-indexer-app)
task-indexer-app --> elasticsearch
notification-indexer-app --> elasticsearch