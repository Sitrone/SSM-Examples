## 多线程下载工具 

## 特性 
1. 多线程分段下载 
2. 支持自动重试 
3. 支持断点续传
4. 当前线程执行结束，支撑从剩余任务中取出一段数据进行继续下载(todo)

## 约束 
server端要支持断点续传，支持请求部分数据，也即要支持请求[Range](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Range_requests)头，响应里面返回`Content-Range`头数据