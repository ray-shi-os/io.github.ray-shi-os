--获取key
local bucket_key = KEYS[1]
--每秒生成的令牌数
local rate = tonumber(ARGV[1])
-- 桶容量
local bucket_count = tonumber(ARGV[2])
--当前时间
local current_time = tonumber(ARGV[3])

-- 检查令牌桶的存在性，不存在则创建
if redis.call('exists', bucket_key) == 0 then
    redis.call('hset', bucket_key, 'token_rate', rate)
    redis.call('hset', bucket_key, 'token_count', bucket_count)
    redis.call('hset', bucket_key, 'token_time', current_time)
end

-- 读取当前令牌桶状态
local token_rate = tonumber(redis.call('hget', bucket_key, "token_rate"))
local token_count = tonumber(redis.call('hget', bucket_key, "token_count"))
local token_time = tonumber(redis.call('hget', bucket_key, "token_time"))

-- 更新时间戳
if current_time > token_time then
    local time_delta = current_time - token_time
    local tokens_to_add = math.floor(time_delta * (token_rate / 1000))
    bucket_count = math.min(token_count + tokens_to_add, bucket_count)
    redis.call('hset', bucket_key, 'token_count', bucket_count)
    redis.call('hset', bucket_key, 'token_time', current_time)
end

-- 判断请求令牌是否足够
if bucket_count < 1 then
    -- 不足
    return 0
else
    bucket_count = bucket_count - 1
    redis.call('hset', bucket_key, 'token_count', bucket_count)
    -- 足够
    return 1
end