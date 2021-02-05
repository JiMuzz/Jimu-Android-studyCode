// IMsgManager.aidl
package com.example.studynote.binder;

// Declare any non-default types here with import statements

interface IMsgManager {

      String getMsg();
      void tell(in String msg);
}