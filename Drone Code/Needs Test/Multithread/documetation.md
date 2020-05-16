Describe and documetation of this code: https://github.com/ivanseidel/ArduinoThread (about thread function/class describe). Thanks Ivan Seidel!
In callback of function for manipulation thread, don't use function delay(), because the task will block. Using void Thread::setInterval(int time) to prevent to block next task; 
If you want few thread and run all equal time, to you need using ThreadController class and add using method of this class .add(). 
Minimal using code for running thread.

```C++

#include <Thread.h>
int ledPin = 13;

//My simple Thread
Thread myThread = Thread();

// callback for myThread
void niceCallback() {
   // add code of task in this thread
}

void setup() {

	myThread.onRun(niceCallback);
	myThread.setInterval(500); // to set interval to next time code run with initail task of callback function
}

void loop() {
	// checks if thread should run
	if(myThread.shouldRun())
		myThread.run();
}
```
Minimal using code for ThreadController 

```C++
#include <Thread.h>
#include <ThreadController.h>

// ThreadController that will controll all threads
ThreadController controll = ThreadController();

// adding class thread
Thread myThread = Thread();
Thread hisThread = Thread();

// callback for myThread
void myThreadCallback() {
    // add code of task in this thread
}

// callback for hisThread
void hisThreadCallback(){
    // add code of task in this thread
}

void setup() {

	// Configure myThread
	myThread.onRun(myThreadCallback); // set callback in this class of thread
	myThread.setInterval(500); // set interval maxiumum to run. If time was expired time to will do task with newly

	// Configure hisThread
	hisThread.onRun(hisThreadCallback); // set callback in this class of thread
	hisThread.setInterval(250); // set interval maxiumum to run. If time was expired time to will do task with newly

	// Adds both threads to the controller
	controll.add(&myThread);  // & to pass the pointer to it
	controll.add(&hisThread); // & to pass the pointer to it
}

void loop(){
	// run ThreadController
	// this will check every thread inside ThreadController,
	// if it should run. If yes, he will run it;
	controll.run();
}
