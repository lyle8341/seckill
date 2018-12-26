package com.lyle.ms.serializers.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.lyle.ms.User2;

/**
 * @author Lyle
 * @version v1.0
 * @desc
 * @date 2018-12-25 下午11:30
 * @since 1.8
 */
public class ProtostuffSerializerService {

  private static final RuntimeSchema<User2> schema = RuntimeSchema.createFrom(User2.class);

  public static User2 getObj(byte[] content) {
    //空对象
    User2 user = schema.newMessage();
    ProtostuffIOUtil.mergeFrom(content, user, schema);
    return user;
  }

  //序列化
  public static byte[] serializeObj(User2 user) {
    return ProtostuffIOUtil
        .toByteArray(user, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
  }
}