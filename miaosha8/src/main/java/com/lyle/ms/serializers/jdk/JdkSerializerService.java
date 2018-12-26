package com.lyle.ms.serializers.jdk;

import com.lyle.ms.User1;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Lyle
 * @version v1.0
 * @desc
 * @date 2018-12-26 上午12:02
 * @jdk 1.8
 */
public class JdkSerializerService {

  public static User1 getObj(byte[] content) throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream(content);
    ObjectInputStream ois = new ObjectInputStream(in);
    return (User1) ois.readObject();
  }

  public static byte[] serializeObj(User1 user1) throws Exception {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(out);
    oos.writeObject(user1);
    oos.flush();
    return out.toByteArray();
  }
}