### 栈与栈帧
Java Virtual Machine Stacks （Java 虚拟机栈）
    
我们都知道 JVM 中由堆、栈、方法区所组成，其中栈内存是给谁用的呢？其实就是线程，每个线程启动后，虚拟
机就会为其分配一块栈内存。
- 每个栈由多个栈帧（Frame）组成，对应着每次方法调用时所占用的内存
- 每个线程只能有一个活动栈帧，对应着当前正在执行的那个方法

### 线程上下文切换（Thread Context Switch）
因为以下一些原因导致 cpu 不再执行当前的线程，转而执行另一个线程的代码

- 线程的 cpu 时间片用完
- 垃圾回收
- 有更高优先级的线程需要运行
- 线程自己调用了 sleep、yield、wait、join、park、synchronized、lock 等方法

当 Context Switch 发生时，需要由操作系统保存当前线程的状态，并恢复另一个线程的状态，Java 中对应的概念
就是程序计数器（Program Counter Register），它的作用是记住下一条 jvm 指令的执行地址，是线程私有的

- 状态包括程序计数器、虚拟机栈中每个栈帧的信息，如局部变量、操作数栈、返回地址等
- Context Switch 频繁发生会影响性能

### 常见方法
    
方法名|static|功能说明|注意
---|---|---|---
start()|no|启动一个新线程，在新的线程运行 run 方法中的代码|start 方法只是让线程进入就绪，里面代码不一定立刻运行（CPU 的时间片还没分给它）。每个线程对象的start方法只能调用一次，如果调用了多次会出现IllegalThreadStateException
run()|no|新线程启动后会调用的方法|如果在构造 Thread 对象时传递了 Runnable 参数，则线程启动后会调用 Runnable 中的 run 方法，否则默认不执行任何操作。但可以创建 Thread 的子类对象，来覆盖默认行为
join()|no|等待线程运行结束
join(long n)|no|等待线程运行结束,最多等待 n毫秒
getId()|no|获取线程长整型的 id|id 唯一
getName()|no|获取线程名
setName(String)|no|修改线程名
getPriority()|no|获取线程优先级
setPriority(int)|no|修改线程优先级|java中规定线程优先级是1~10 的整数，较大的优先级能提高该线程被 CPU 调度的机率
getState()|no|获取线程状态|Java 中线程状态是用 6 个 enum 表示，分别为：NEW, RUNNABLE, BLOCKED, WAITING,TIMED_WAITING, TERMINATED
isInterrupted()|no|判断是否被打断，|不会清除 打断标记
isAlive()|no|线程是否存活（还没有运行完毕）
interrupt()|no|打断线程|如果被打断线程正在 sleep，wait，join 会导致被打断的线程抛出 InterruptedException，并清除 打断标记 ；如果打断的正在运行的线程，则会设置 打断标记 ；park 的线程被打断，也会设置 打断标记
interrupted()|yes|判断当前线程是否被打断|会清除 打断标记
currentThread()|yes|获取当前正在执行的线程
sleep(long n)|yes|让当前执行的线程休眠n毫秒,休眠时让出 cpu的时间片给其它线程
yield()|yes|提示线程调度器让出当前线程对CPU的使用|主要是为了测试和调试

### 线程的状态
#### 从操作系统层面来描述（五种状态）
- 初始状态：仅是在语言层面创建了线程对象，还未与操作系统线程关联
- 可运行状态：（就绪状态）指该线程已经被创建（与操作系统线程关联），可以由cpu调度执行
- 运行状态：指获取了cpu时间片运行中的状态
    - 当cpu时间片用完，会从【运行状态】转换至【可运行状态】，会导致线程的上下文切换
- 阻塞状态：
    - 如果调用了阻塞 API，如 BIO 读写文件，这时该线程实际不会用到 CPU，会导致线程上下文切换，进入【阻塞状态】
    - 等 BIO 操作完毕，会由操作系统唤醒阻塞的线程，转换至【可运行状态】
    - 与【可运行状态】的区别是，对【阻塞状态】的线程来说只要它们一直不唤醒，调度器就一直不会考虑
      调度它们
- 终止状态：表示线程已经执行完毕，生命周期已经结束，不会再转换为其它状态

#### 从JAVA API层面来描述（六种状态）
- NEW 线程刚被创建，但是还没有调用 start() 方法
- RUNNABLE 当调用了 start() 方法之后，注意，Java API 层面的 RUNNABLE 状态涵盖了 操作系统 层面的
【可运行状态】、【运行状态】和【阻塞状态】（由于 BIO 导致的线程阻塞，在 Java 里无法区分，仍然认为
是可运行）
- BLOCKED ， WAITING ， TIMED_WAITING 都是 Java API 层面对【阻塞状态】的细分，后面会在状态转换一节
详述
- TERMINATED 当线程代码运行结束

link: https://www.bilibili.com/video/BV16J411h7Rd?p=11