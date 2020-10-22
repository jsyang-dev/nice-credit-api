package link.credit.common.exception;

import link.credit.common.constant.FullTextCode;
import link.credit.common.constant.NiceDecodeCode;
import link.credit.common.constant.NiceEncodeCode;

public class NiceException extends RuntimeException {

  public NiceException(NiceEncodeCode niceEncodeCode) {
    super(
        niceEncodeCode.toString() + " iReturn: " + niceEncodeCode.getCode());
  }

  public NiceException(NiceDecodeCode niceDecodeCode) {
    super(
        niceDecodeCode.toString() + " iReturn: " + niceDecodeCode.getCode());
  }

  public NiceException(FullTextCode fullTextCode) {
    super(fullTextCode.toString() + " resCode: " + fullTextCode.getCode());
  }

  public NiceException(String message) {
    super(message);
  }
}
