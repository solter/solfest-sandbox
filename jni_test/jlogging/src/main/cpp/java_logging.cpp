#include "logging.h"
#include <iostream>

/*
 * References to the necessary java objects/methods to call the logger.
 * These are stored as static global variables to make the log statements
 * easier to call.
 */
static JNIEnv * g_env;
static jobject g_logger;
static jmethodID g_debugId;
static jmethodID g_infoId;
static jmethodID g_warnId;
static jmethodID g_errorId;

/**
 * Grabs the field ID pointing to the logger,
 * if it exists.
 */
jfieldID getLoggerId(jclass self){
   // TRY FOR A STATIC FIELD
   
   // try to grab static "LOGGER"
   jfieldID loggerId = g_env->GetStaticFieldID(self, "LOGGER", "Lorg/slf4j/Logger;");
   if (loggerId != NULL) return loggerId;

   //clear the exception, and try for static "logger"
   g_env->ExceptionClear();
   loggerId = g_env->GetStaticFieldID(self, "logger", "Lorg/slf4j/Logger;");
   if (loggerId != NULL) return loggerId;

   //clear the exception, and try for static "Logger"
   g_env->ExceptionClear();
   loggerId = g_env->GetStaticFieldID(self, "Logger", "Lorg/slf4j/Logger;");
   if (loggerId != NULL) return loggerId;

   //clear the exception, and try for static "LOG"
   g_env->ExceptionClear();
   loggerId = g_env->GetStaticFieldID(self, "LOG", "Lorg/slf4j/Logger;");
   if (loggerId != NULL) return loggerId;

   // TRY FOR AN INSTANCE FIELD
   
   // clear the exception, try to grab "LOGGER"
   g_env->ExceptionClear();
   loggerId = g_env->GetFieldID(self, "LOGGER", "Lorg/slf4j/Logger;");
   if (loggerId != NULL) return loggerId;

   //clear the exception, and try for "logger"
   g_env->ExceptionClear();
   loggerId = g_env->GetFieldID(self, "logger", "Lorg/slf4j/Logger;");
   if (loggerId != NULL) return loggerId;

   //clear the exception, and try for "Logger"
   g_env->ExceptionClear();
   loggerId = g_env->GetFieldID(self, "Logger", "Lorg/slf4j/Logger;");
   if (loggerId != NULL) return loggerId;

   //clear the exception, and try for "LOG"
   g_env->ExceptionClear();
   loggerId = g_env->GetFieldID(self, "LOG", "Lorg/slf4j/Logger;");

   // return this result whether or not it is null
   return loggerId;

}

/*
 * Create global references to the logger.
 */
void initializeLogger(JNIEnv * env, jobject obj){
   // save off the environment
   g_env = env;

   // save off the logger
   jclass self = g_env->GetObjectClass(obj);
   jfieldID loggerId = getLoggerId(self);
   // return, and let the caller handle the exception
   if (loggerId == NULL) return;

   g_logger = g_env->GetStaticObjectField(self, loggerId);

   // save off the levels
   jclass loggerClass = g_env->GetObjectClass(g_logger);
   g_debugId = g_env->GetMethodID(loggerClass, "debug", "(Ljava/lang/String;)V");
   g_infoId = g_env->GetMethodID(loggerClass, "info", "(Ljava/lang/String;)V");
   g_warnId = g_env->GetMethodID(loggerClass, "warn", "(Ljava/lang/String;)V");
   g_errorId = g_env->GetMethodID(loggerClass, "error", "(Ljava/lang/String;)V");

   return;
}

void logDebug(const char* message){
   g_env->CallVoidMethod(g_logger, g_debugId, g_env->NewStringUTF(message));
}

void logInfo(const char* message){
   g_env->CallVoidMethod(g_logger, g_infoId, g_env->NewStringUTF(message));
}

void logWarn(const char* message){
   g_env->CallVoidMethod(g_logger, g_warnId, g_env->NewStringUTF(message));
}

void logError(const char* message){
   g_env->CallVoidMethod(g_logger, g_errorId, g_env->NewStringUTF(message));
}
