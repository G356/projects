# boot-security

docker run -p 6379:6379 --name myredis \
-v $PWD/conf/redis.conf:/etc/redis/redis.conf -v $PWD/data:/data \
-d redis:latest redis-server  /etc/redis/redis.conf \
--appendonly yes



docker run -p 6379:6379 -v $PWD/data:/data  -d \
redis:3.2 redis-server --appendonly yes
