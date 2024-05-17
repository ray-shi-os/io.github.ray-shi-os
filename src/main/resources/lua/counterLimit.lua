--获取KEY
local key = KEYS[1]
-- 限流时间/缓存时间
local time = tonumber(ARGV[1])

redis.log(redis.LOG_NOTICE, time)
--当前请求数
local currentCount = redis.call('incr', key)
if (tonumber(currentCount) == 1) then
    redis.call('expire', key,  time)
end
return currentCount


