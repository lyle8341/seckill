package com.lyle.ms.result;

public class Result<T> {

  private int code;
  private String msg;
  private T data;

  /**
   * 成功时候的调用
   */
  public static <T> Result<T> success(T data) {
    return new Result<T>(data);
  }

  /**
   * 失败时候的调用
   */
  public static <T> Result<T> error(CodeMsg cm) {
    return new Result<T>(cm);
  }

  private Result(T data) {
    code = 0;
    msg = "success";
    this.data = data;
  }

  private Result(CodeMsg cm) {
    if (cm == null) {
      return;
    }
    code = cm.getCode();
    msg = cm.getMsg();
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public T getData() {
    return data;
  }
}
