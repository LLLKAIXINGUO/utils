# windows下  查看端口被占用情况以及杀死端口号进程

### 查看端口使用情况

netstat -ano |findstr "端口号"

### 杀死端口号进程

taskkill -PID  12315 -F



![image-20211127142503310](G:%5CTYPORA%E6%96%87%E6%A1%A3%E5%A1%AB%E5%85%85%5C%E9%81%87%E8%A7%81%E7%9A%84%E5%9D%91%E4%BB%A5%E5%8F%8A%E9%94%99%E8%AF%AF%E3%80%81%E5%BC%82%E5%B8%B8%5Cwindows%E4%B8%8B%20%20%E6%9F%A5%E7%9C%8B%E7%AB%AF%E5%8F%A3%E8%A2%AB%E5%8D%A0%E7%94%A8%E6%83%85%E5%86%B5%E4%BB%A5%E5%8F%8A%E6%9D%80%E6%AD%BB%E7%AB%AF%E5%8F%A3%E5%8F%B7%E8%BF%9B%E7%A8%8B.assets%5Cimage-20211127142503310-1640053443037.png)