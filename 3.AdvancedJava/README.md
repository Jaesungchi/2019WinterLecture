# AdvancedJava_1:green_book:

![imge](https://img.shields.io/badge/ProjectType-Lecture-green)  ![imge](https://img.shields.io/badge/Language-Java-yellow)  ![imge](https://img.shields.io/badge/Tools-IntelliJ-blue)

![imge](https://img.shields.io/badge/Language-SQL-orange) ![imge](https://img.shields.io/badge/Tools-MySQL-blue)

2019 - 12 - 26

## Thread :arrow_right:

### 개요

Thread는 기본적으로 운영체제와 관련있음, Thread를 이해하기 위해서는 Process를 이해해야함.

Process가 실행중인 경우, Thread는 프로그램이면서 여러작업을 실행할 목적으로 생성한 실행과 흐름의 단위로 경량 Process라고도 한다.

Thread는 프로세스 안에서 Stack을 제외한 시스템 자원을 공유한다. 따라서 process를 여러 개 실행하는 것 보다 시스템 자원을 절약할 수 있다.

### 장점:thumbsup:

- 시스템 자원 절약
- 사용자에 대한 응답성 향상
- 프로세스 간 통신을 이용한 병렬 작업보다 Thread를 이용한 병렬 작업이 간단하고 빠르다.
- 시스템 처리 성능을 향상시킬 수 있다.

### 단점:thumbsdown:

- 프로세스 밖에서 Thread 제어 불가.
- Multi Thread 프로그램의 경우 오류 발생 가능성이 높고, 잘못된 프로그램은 시스템에 영향을 준다.
- 프로그램 디버깅이 상대적으로 어렵다.
- 단일 프로세스 시스템에서는 큰 효과를 기대하기 어렵다.
- Synchronization,DeadLock 같은 문제를 고려해야 함.

### Thread 상태

![image](https://user-images.githubusercontent.com/37828448/71458529-7ceba200-27e6-11ea-8115-55a27213b381.png)

```java
static int activeConut(); //활동중인 Thread 갯수 반환
static Thread currentThread(); //현재 실행 중인 Thread 객체 반환
long getId(); //Thread ID 반환
String getname(); //Thread 이름을 반환
int gerPriority(); //Thread의 우선순위 반환
Boolean isAlive(); //Thread가 살았는지 죽었는지 확인.
Boolean isDeamon(); //Deamon 쓰레드를 확인
void join(); //현재Thread가 죽지 않게 멈추고, 우선적으로 실행한다. 
void join(long millis); //millis 만큼 우선적으로 실행
void setName(String name); //Thread 이름 설정
void setPriority(int newPriority); //Thread의 우선순위 설정
static void sleep(long millis); //millis 값만 큼 Thread 정지
void start(); //Thread 시작
static void yield(); //현재 실행 중인 Thread를 멈추고 다른 Thread를 실행
```

### 구현 방법:blue_book:

1. Thread 클래스를 상속받아 Run 메소드를 오버라이딩 하여 사용

   - 가장 일반적인 방법이지만 다중 상속 불가능.

   ```java
   public class Test extends Thread{
       public void run(){
           //Thread 동작
       }
       public static void main(String[] args){
           Test test = new Test();
           test.start(); //Thread 시작
       }
   }
   ```

2. Runnable 인터페이스를 사용해 Thread 객체를 생성하는 방법.

   - Thread 동작 클래스가 다른 클래스를 상속 받아야할 때에는 Runnable 인터페이스로 한다.
   - Thread 클래스를 상속할 때처럼 내부에 run 메소드만 오버라이딩한다.
   - Thread를 상속받을 때와 구조상 차이점이 없다.

   ```java
   public class Test implements Runnable{
       @Override
       public void run(){
           //Thread 동작
       }
       public static void main(String[] args){
           Test test = new Test();
           test.start();
       }
   }
   ```

## Multi-Thread 프로그래밍:computer:

### 개요

Multi-Thread는 말 그대로 하나의 작업 수행을 목적으로 여러개의 Thread를 사용하는 기법

### Multi-Thread로 실행하는 어플리케이션 개발

- 개발 전, 몇 개의 작업을 별렬로 실행할지 결정되는게 선행되어야 함.

  ```java
  public class Test extends Thread{
  	public void run(){
  		String tname = Thread.currentThread().getName();
  		System.out.println(tname + "시작");
  		for(int i = 0 ; i < 10; i++)
  			System.out.println(tname + "-" + i);
  		Systm.out.println(tname + "종료");
  	}
  	public static void main(String[] args){
  		Test test= new Test();
  		for(int i = 0 ; i < 2; i++){
  			Thread t = new Thread(app);
  			t.start();
  		}
  	}
  }
  ```

  ```
  실행 결과
  Thread-1 시작 //Main 종료 후 Thread 시작.
  Thread-1-1   //Thread-1부터 시작.
  .
  .
  .
  Thread-1 종료
  Thread-2 시작
  Thread-2-1
  .
  .
  Thread-2 종료
  ```

  - 여기서 스레드간 실행 순서를 비순차적으로 실행하고 싶다면 Thread.sleep(Int)를 추가한다.

  ```java
  for(int i = 0 ; i < 10; i++){
  	System.out,println(this.getName() + "-" + i);
  	try{
  		Thread.sleep(1000);
  	} catch(InterruptedException e){
  		e.printStackTrace();
  	}
  }
  ```

- Multi_Thread는 순차적인 실행을 보장하지 않는다. 따라서, 순차적인 실행하는 것이 중요하다면 setPriority() 메소드를 기반한 우선순위 지정이나 join() 문을 사용하여 스레드를 종료하는 기법이 필요함.
- 일반적으로 Thread 자체는 병렬 실행을 목적으로 하므로 순서는 중요하지 않다.

## Thread Synchronized :clock1:

### 개요

Thread Synchronized 는 Thread 사용으로 발생하는 자원 사용 문제를 해결하는 기법.

### 문제점

- Thread 접근 변수의 life cycle이 끝나는 문제.
  - Thread 참조 변수를 메서드에서 로컬 변수로 선언한 경우, 메서드가 종료 되면서 변수 또한 메모리에서 사라진다. 이 때 Thread는 계속 실행 중일 수 있으나 참조가 사라져서 제어가 안된다.
- Thread 우선권 문제
  -  프로그램에서 특정 Thread의 작업을 우선적으로 처리해야 할때 발생, 우선권이 낮은 Thread는 작업을 종료하지 못하기도 한다.
- 공용 리소스 접근 문제

### Synchronized 키워드

- 자바에서 Thread를 동기화 하려면, 동기화를 원하는 메소드나 블록에 synchronized 키워드를 붙인다.
- 다만 리소스 동기화 처리를 제대로 하지 않으면 속도 저하등 문제가 발생한다.(block상태가 되는경우)

```java
public synchronized void method(){
    //동기화 메소드
}
public void method(){
    // 실행
    synchronized(공유객체){
        //단 하나의 Thread만 실행.
    }
    //여러 Thread 실행 가능 영역
}
```

## Thread State Control

- Blocked -> Runnable 메소드
  - interrupt() : 바로 block 상태가 되지 않음.
  - notify()
  - notifyAll()
- Run -> Blocked 메소드
  - sleep() : 일시 정지 상태에서 interrupt 메소드 호출
  - join() : MainThread가 정지 됨. (내가 수행한 다음에 네가 수행해)
  - wait()
- Run -> Runnalbe 메소드
  - yield() : 실행 상태인 Thread가 ready가 되고 ready에 있던게 실행됨.
- Thread를 종료하기 위해서는 Run 메소드를 정상적으로 종료하는 방법이 있음.

```java
public class YieldExample(){
	public static void main(String[] args){
		ThreadA threadA = new ThreadA();
		ThreadA threadB = new ThreadA();
		
		threadA.start();
		threadB.start();
		
		try{Thrad.sleep();}catch(InterruptedException e){}
		threadA.work = false;
		
		try{Thread.sleep();}catch(InterruptedException e){}
		threadA.stop = true;
		threadB.stop = true;
	}
	
	class ThreadA extends Thread(){
		public boolean stop = false;
		public boolean work = true;
		
		public void run(){
			while(!stop){ //while로 감싸서 정상적으로 종료할 수 있게 함.
				if(work){
					//ThreadA 작업 내용
				} else {
					Thread.yield();
				}
			}
			//ThreadA 종료
		}
	}
}
```

- 강제로 종료하기 위해서는 interrupt를 건다. (이때는 정상적으로 돌다가 끝났을때 interrupt를 한다. 바로 멈추는게 아님.)

- wait(), notify(), notifyAll() 은 두 개의 스레드가 교대로 번갈아가며 실행할때 사용.

  ![image](https://user-images.githubusercontent.com/37828448/71464034-a95de900-27fb-11ea-8d60-7473cf16ba32.png)

### Daemon Thread

- 주 Thread의 작업을 돕는 보조적인 역할을 수행하는 Thread

  ```java
  thread.setDaemon(true); //Daemon으로 바꿈
  thread.start();
  thread.isDaemon(); //리턴값으로 확인
  ```

- 주 Thread가 종료되면 자동으로 종료

  - 워드의 자동저장, 미디어플레이어의 음악재생 등.

```java
public class DaemonExample extends Thread{
	public void save(){
		//저장
	}
	
	@Override
	public void run(){
		while(true){ //데몬은 종료되면 알아서 종료됨
			try{
				Thread.sleep(1000);
			} catch (InterruptedException e){
				break;
			}
			save();
		}
	}
}
```

