#include "com_sorter_Sorter.h"
#include <cstdlib>
#include <iostream>
#include <vector>
#include <algorithm>

JNIEXPORT jdoubleArray JNICALL Java_com_sorter_Sorter_sort01
  (JNIEnv *env, jobject thisObject, jdoubleArray arrayA, jboolean order)
  {
      jsize size = env->GetArrayLength(arrayA);
      std::vector<double> input(size);
     	env->GetDoubleArrayRegion(arrayA, 0, size, &input[0]);
      sort(input.begin(), input.end());
      if(order == 0)
      {
        reverse(input.begin(), input.end());
      }
      jdoubleArray output = env->NewDoubleArray(size);
     	env->SetDoubleArrayRegion(output, 0, size, &input[0]);
      std::cout << "This is native method";
      return output;
  }
