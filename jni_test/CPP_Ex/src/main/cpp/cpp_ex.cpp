#include "com_solfest_cppJniTrivial_TrivialNative.h"
#include "logging.h"

extern "C" { /* Specify the C calling convention */
    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    !! WARNING: printing to stdout causes problems !!
    !!         during the Maven tests              !!
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

    int sum = 0;

    /*
     * Class:     com_solfest_cppJniTrivial_TrivialNative
     * Method:    noStateNative
     * Signature: ()Ljava/lang/String;
     *
     * Inputs:
     *    env    -> reference to the java environment as exposed by the JNI interface
     *    obj    -> reference to the instance of the java class making the call ("this")
     */
    JNIEXPORT jstring JNICALL Java_com_solfest_cppJniTrivial_TrivialNative_noStateNative
      (JNIEnv * env, jobject obj){
        initializeLogger(env, obj);
        logInfo("Inside cpp noStateNative method");

        // return a string
        return env->NewStringUTF("Successfully called stateless native code");
    }

    /*
     * Class:     com_solfest_cppJniTrivial_TrivialNative
     * Method:    stateNative
     * Signature: (I)Ljava/lang/String;
     *
     * Inputs:
     *    env    -> reference to the java environment as exposed by the JNI interface
     *    obj    -> reference to the instance of the java class making the call ("this")
     *    input  -> an input integer
     */
    JNIEXPORT jstring JNICALL Java_com_solfest_cppJniTrivial_TrivialNative_stateNative
      (JNIEnv * env, jobject obj, jint input){
        initializeLogger(env, obj);
        logInfo("Inside cpp stateNative method");

        // update the state
        sum += input;

        // form the state string
        char buffer[30];
        sprintf(buffer, "input was %2d, sum is now %3d", input, sum);

        // return the state string
        return env->NewStringUTF(buffer);
    }

} /* End Extern C */
