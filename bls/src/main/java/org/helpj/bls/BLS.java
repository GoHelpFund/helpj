/*
 * Copyright 2018 Help Core Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file was generated by SWIG (http://www.swig.org) and modified.
 * Version 3.0.12
 */

package org.helpj.bls;

import com.google.common.base.Preconditions;

public class BLS {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  public static final String HELPJ_VERSION = "0.16.3";


  static {
    boolean isLibraryLoaded;
    try
    {
      System.loadLibrary(JNI.LIBRARY_NAME);
      Preconditions.checkState(GetVersionString().equals(HELPJ_VERSION),
              "helpjbls:  C++ Source Version doesn't match Java Source version:" +
              "C++: " + GetVersionString() + " Java: " + HELPJ_VERSION);
      isLibraryLoaded = true;
    } catch(UnsatisfiedLinkError x) {
      throw new RuntimeException(x.getMessage());
    }
  }

  protected BLS(long cPtr, boolean cMemoryOwn) {
    Preconditions.checkArgument(cPtr != 0);
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BLS obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        JNI.delete_BLS(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static String getGROUP_ORDER() {
    return JNI.BLS_GROUP_ORDER_get();
  }

  public static boolean Init() {
    return JNI.BLS_Init();
  }

  public static void HashPubKeys(SWIGTYPE_p_bn_t output, long numOutputs, MessageHashVector serPubKeys, SWIGTYPE_p_std__vectorT_size_t_t sortedIndices) {
    JNI.BLS_HashPubKeys(SWIGTYPE_p_bn_t.getCPtr(output), numOutputs, MessageHashVector.getCPtr(serPubKeys), serPubKeys, SWIGTYPE_p_std__vectorT_size_t_t.getCPtr(sortedIndices));
  }

  /*  Help Core Only
  public static PrivateKey PrivateKeyShare(PrivateKeyVector sks, byte [] id) {
    return new PrivateKey(JNI.BLS_PrivateKeyShare(PrivateKeyVector.getCPtr(sks), id), true);
  }

  public static PrivateKey RecoverPrivateKey(PrivateKeyVector sks, MessageHashVector ids) {
    return new PrivateKey(JNI.BLS_RecoverPrivateKey(PrivateKeyVector.getCPtr(sks), MessageHashVector.getCPtr(ids)), true);
  }

  public static PublicKey PublicKeyShare(PublicKeyVector pks, byte [] id) {
    return new PublicKey(JNI.BLS_PublicKeyShare(PublicKeyVector.getCPtr(pks), id), true);
  }

  public static PublicKey RecoverPublicKey(PublicKeyVector sks, MessageHashVector ids) {
    return new PublicKey(JNI.BLS_RecoverPublicKey(PublicKeyVector.getCPtr(sks), MessageHashVector.getCPtr(ids)), true);
  }

  public static InsecureSignature SignatureShare(InsecureSignatureVector sks, byte [] id) {
    return new InsecureSignature(JNI.BLS_SignatureShare(InsecureSignatureVector.getCPtr(sks), id), true);
  }

  public static InsecureSignature RecoverSig(InsecureSignatureVector sigs, MessageHashVector ids) {
    return new InsecureSignature(JNI.BLS_RecoverSig(InsecureSignatureVector.getCPtr(sigs), MessageHashVector.getCPtr(ids)), true);
  }

  public static PublicKey DHKeyExchange(PrivateKey privKey, PublicKey pubKey) {
    return new PublicKey(JNI.BLS_DHKeyExchange(PrivateKey.getCPtr(privKey), PublicKey.getCPtr(pubKey)), true);
  }
  */

  public static void CheckRelicErrors() {
    JNI.BLS_CheckRelicErrors();
  }

  public BLS() {
    this(JNI.new_BLS(), true);
  }

  public final static long MESSAGE_HASH_LEN = JNI.BLS_MESSAGE_HASH_LEN_get();

  static long GetContext() {
    return JNI.BLS_GetContext();
  }
  static long GetContextError() {
    return JNI.BLS_GetContextError();
  }

  public static long STS_OK = 0;
  public static long STS_ERR = 1;
  static void SetContextError(long error) {
    JNI.BLS_SetContextError(error);
  }

  static String GetVersionString() { return JNI.BLS_GetVersionString(); }

}
