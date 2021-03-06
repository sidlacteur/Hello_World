#include <stdio.h> 
#include <pthread.h> 
#include <stdlib.h>
#include <math.h> 		// used for the floor operations
#include <unistd.h>		/* for gettimeofday(), getpid() */
#include "ThreadDemoChange.h"
#include <sched.h>		/* for scheduling calls/constants */
#include <unistd.h>
#include <sys/types.h>
#include <sys/syscall.h>

//struct's define
struct sched_param param;


void PrintThreadId()
{

	printf("\n************* GET_THREAD_ID ***********************\n");
	printf("\ngetpid: %d \ngetpthread_self: %d \nt_id:%lu\n",getpid(), (int)pthread_self(), syscall(SYS_gettid));	
    	//printf("\tThe PTHREAD_SElF worker = %zu\n",(intptr_t) (void *)pthread_self());

}



int TId()
{

	printf("\n************* GET_THREAD_ID ***********************\n");
	return (unsigned int)pthread_self();	
   
}


pthread_t TId_1()
{

	printf("\n************* GET_THREAD_ID ***********************\n");
	return pthread_self();	
   
}



JNIEXPORT void JNICALL Java_ThreadDemoChange_PrintPID
  (JNIEnv *env, jobject o, jint i)
{
	PrintThreadId();
}


JNIEXPORT jint JNICALL Java_ThreadDemoChange_GetTID
  (JNIEnv *env, jobject o)
{
	jint ret=TId();
	printf("depuis le C TIDUnsignedint() %d et TIDInt() est %zu", ret,(intptr_t) (void *)TId_1());
	return ret;
}


JNIEXPORT void JNICALL Java_ThreadDemoChange_ChangePrio2
  (JNIEnv *env, jobject o, jint PthreadID, jint EditPrio)
{

	pthread_t truc;	
	param.sched_priority=EditPrio;

    	truc = 	(pthread_t) PthreadID;
	printf("Je suis la %lu et %d", truc, PthreadID);

	//int x = pthread_setschedprio(truc, EditPrio);

	//int y = pthread_setschedparam(truc,SCHED_RR,&param);
	/*
	printf("\nSET_PRIORITY with   SCHED_RR policy = %d \n Test_set_SCHED= %d \t \n  TEST_set_param = 			    		\n",param.sched_priority,y);

	if( y== -1 ) {
		fprintf(stderr,"error setting scheduler ... are you 			root?\n");
		exit(1);
	}
 */
}

