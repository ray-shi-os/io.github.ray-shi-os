-- 限流器的键名
local key = KEYS[1]
-- 漏桶的容量
local capacity = tonumber(ARGV[1])
-- 漏桶的速率
local rate = tonumber(ARGV[2])
-- 当前时间戳
local current_time = tonumber(ARGV[3])
-- 获取漏桶中的水滴数量
local water = tonumber(redis.call('get', key) or "0")
-- 上次漏水的时间戳
local lastLeakTime = tonumber(redis.call('get', key .. ':last_leak_time') or current_time)
-- 计算当前时间与上次漏水的时间间隔
local elapsed = math.max(0, current_time - lastLeakTime)
-- 根据时间间隔计算漏水数量，并更新漏桶中的水滴数量
water = water - math.floor(rate*(elapsed/1000))
-- 水滴数量不会低于0
if water < 0 then
    water = 0
end
-- 新的请求加入漏桶中
water = water + 1
if water > capacity then
    -- 漏桶已满，拒绝请求
    return 0
else
    -- 接受请求，更新漏桶中的水滴数量
    redis.call('set', key, water)
    -- 更新上次漏水的时间戳
    redis.call('set', key .. ':last_leak_time', current_time)
    return 1
end
