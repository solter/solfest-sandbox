/*
 * This file defines methods which allow access to the
 * java loggers from within native code.
 */
#include <jni.h>

#ifndef _Included_logging
#define _Included_logging

void initializeLogger(JNIEnv * env, jobject obj);

#ifdef __cplusplus
extern "C" {
#endif
void logDebug(const char* message);
void logInfo(const char* message);
void logWarn(const char* message);
void logError(const char* message);
#ifdef __cplusplus
}
#endif

#endif
