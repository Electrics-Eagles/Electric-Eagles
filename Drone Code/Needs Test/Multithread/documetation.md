Describe and documetation of this code: https://github.com/ivanseidel/ArduinoThread (about thread function/class describe). Thanks Ivan Seidel!
In callback of function for manipalation thread, don't use function delay(), because the task will block. Using void Thread::setInterval(int time) to prevent to block next task;
Minimal using code for running thread. 

```C++

#include <Thread.h>
int ledPin = 13;

//My simple Thread
Thread myThread = Thread();

// callback for myThread
void niceCallback() {

}

void setup(){
	Serial.begin(9600);

	pinMode(ledPin, OUTPUT);

	myThread.onRun(niceCallback);
	myThread.setInterval(500); // to set interval to next time code run with initail task of callback function
}

void loop(){
	// checks if thread should run
	if(myThread.shouldRun())
		myThread.run();
}

