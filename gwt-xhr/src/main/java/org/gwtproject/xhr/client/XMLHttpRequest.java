/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.xhr.client;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import org.gwtproject.typedarrays.shared.ArrayBuffer;

/**
 * The native XMLHttpRequest object. Most applications should use the higher- level {@link
 * org.gwtproject.http.client.RequestBuilder} class unless they need specific functionality provided
 * by the XMLHttpRequest object.
 *
 * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/" >http://www.w3.org/TR/XMLHttpRequest/</a>/
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class XMLHttpRequest {

  /** When constructed, the XMLHttpRequest object must be in the UNSENT state. */
  @JsOverlay public static int UNSENT = 0;
  /**
   * The OPENED state is the state of the object when the open() method has been successfully
   * invoked. During this state request headers can be set using setRequestHeader() and the request
   * can be made using send().
   */
  @JsOverlay public static int OPENED = 1;

  /*
   * NOTE: Testing discovered that for some bizarre reason, on Mozilla, the
   * JavaScript <code>XmlHttpRequest.onreadystatechange</code> handler
   * function maybe still be called after it is deleted. The theory is that the
   * callback is cached somewhere. Setting it to null or an empty function does
   * seem to work properly, though.
   *
   * On IE, setting onreadystatechange to null (as opposed to an empty function)
   * sometimes throws an exception.
   *
   * End result: *always* set onreadystatechange to an empty function (never to
   * null).
   */
  /**
   * The HEADERS_RECEIVED state is the state of the object when all response headers have been
   * received.
   */
  @JsOverlay public static int HEADERS_RECEIVED = 2;
  /**
   * The LOADING state is the state of the object when the response entity body is being received.
   */
  @JsOverlay public static int LOADING = 3;
  /**
   * The DONE state is the state of the object when either the data transfer has been completed or
   * something went wrong during the transfer (infinite redirects for instance).
   */
  @JsOverlay public static int DONE = 4;
  /** the onreadystatechange function of the request */
  public Function onreadystatechange;
  /** the response object contains the body of the response */
  public Object response;

  protected XMLHttpRequest() {}

  /**
   * Creates an XMLHttpRequest object.
   *
   * @return the created object
   */
  @JsOverlay
  public static XMLHttpRequest create() {
    return new XMLHttpRequest();
  }

  /**
   * Aborts the current request.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-abort-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-abort-method</a>.
   */
  public native void abort();

  /**
   * Clears the {@link ReadyStateChangeHandler}.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#handler-xhr-onreadystatechange"
   * >http://www.w3.org/TR/XMLHttpRequest/#handler-xhr-onreadystatechange</a>.
   *
   * @see #clearOnReadyStateChange()
   */
  @JsOverlay
  public final void clearOnReadyStateChange() {
    onreadystatechange = () -> {};
  }

  /**
   * Gets all the HTTP response headers, as a single string.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-getallresponseheaders-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-getallresponseheaders-method</a>.
   *
   * @return the response headers.
   */
  public final native String getAllResponseHeaders();

  /**
   * Get's the current ready-state.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#dom-xmlhttprequest-readystate"
   * >http://www.w3.org/TR/XMLHttpRequest/#dom-xmlhttprequest-state</a>.
   *
   * @return the ready-state constant
   */
  @JsProperty(name = "readyState")
  public final native int getReadyState();

  /**
   * Get the response as an {@link ArrayBuffer}.
   *
   * @return an {@link ArrayBuffer} containing the response, or null if the request is in progress
   *     or failed
   */
  @JsOverlay
  public final ArrayBuffer getResponseArrayBuffer() {
    return Js.cast(response);
  }

  /**
   * Gets an HTTP response header.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-getresponseheader-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-getresponseheader-method</a>.
   *
   * @param header the response header to be retrieved
   * @return the header value
   */
  public final native String getResponseHeader(String header);

  /**
   * Gets the response text.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-responsetext-attribute"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-responsetext-attribute</a>.
   *
   * @return the response text
   */
  @JsProperty(name = "responseText")
  public final native String getResponseText();

  /**
   * Gets the response type.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-responsetype-attribute"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-responsetype-attribute</a>
   *
   * @return the response type
   */
  @JsProperty(name = "responseType")
  public final native String getResponseType();

  /**
   * Sets the response type.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-responsetype-attribute"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-responsetype-attribute</a>
   *
   * @param responseType the type of response desired. See {@link ResponseType} for limitations on
   *     using the different values
   */
  @JsOverlay
  public final void setResponseType(ResponseType responseType) {
    this.setResponseType(responseType.getResponseTypeString());
  }

  /**
   * Sets the response type.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-responsetype-attribute"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-responsetype-attribute</a>
   *
   * @param responseType the type of response desired. See {@link ResponseType} for limitations on
   *     using the different values
   */
  @JsProperty(name = "responseType")
  public final native void setResponseType(String responseType);

  /**
   * Gets the status code.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-status-attribute"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-status-attribute</a>.
   *
   * @return the status code
   */
  @JsProperty(name = "status")
  public final native int getStatus();

  /**
   * Gets the status text.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-statustext-attribute"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-statustext-attribute</a>.
   *
   * @return the status text
   */
  @JsProperty(name = "statusText")
  public final native String getStatusText();

  /**
   * Opens an asynchronous connection.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-open-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-open-method</a>.
   *
   * @param httpMethod the HTTP method to use
   * @param url the URL to be opened
   */
  @JsOverlay
  public final void open(String httpMethod, String url) {
    open(httpMethod, url, true);
  }

  /**
   * Opens an asynchronous connection.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-open-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-open-method</a>.
   *
   * @param httpMethod the HTTP method to use
   * @param url the URL to be opened
   * @param user user to use in the URL
   */
  @JsOverlay
  public final void open(String httpMethod, String url, String user) {
    open(httpMethod, url, true, user);
  }

  /**
   * Opens an asynchronous connection.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-open-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-open-method</a>.
   *
   * @param httpMethod the HTTP method to use
   * @param url the URL to be opened
   * @param user user to use in the URL
   * @param password password to use in the URL
   */
  @JsOverlay
  public final void open(String httpMethod, String url, String user, String password) {
    open(httpMethod, url, true, user, password);
  }

  /**
   * Opens an asynchronous or a synchronous connection.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-open-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-open-method</a>.
   *
   * @param httpMethod the HTTP method to use
   * @param url the URL to be opened
   * @param async connection is asynchronous when true, otherwise connection is synchronous
   */
  public final native void open(String httpMethod, String url, boolean async);

  /**
   * Opens an asynchronous or a synchronous connection.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-open-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-open-method</a>.
   *
   * @param httpMethod the HTTP method to use
   * @param url the URL to be opened
   * @param async connection is asynchronous when true, otherwise connection is synchronous
   * @param user user to use in the URL
   */
  public final native void open(String httpMethod, String url, boolean async, String user);

  /**
   * Opens an asynchronous or a synchronous connection.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-open-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-open-method</a>.
   *
   * @param httpMethod the HTTP method to use
   * @param url the URL to be opened
   * @param async connection is asynchronous when true, otherwise connection is synchronous
   * @param user user to use in the URL
   * @param password password to use in the URL
   */
  public final native void open(
      String httpMethod, String url, boolean async, String user, String password);

  /**
   * Initiates a request with no request data. This simply calls {@link #send(String)} with <code>
   * null</code> as an argument, because the no-argument <code>send()</code> method is unavailable
   * on Firefox.
   */
  @JsOverlay
  public final void send() {
    send(null);
  }

  /**
   * Initiates a request with data. If there is no data, specify null.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-send-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-send-method</a>.
   *
   * @param requestData the data to be sent with the request
   */
  public final native void send(String requestData);

  /**
   * Sets the {@link ReadyStateChangeHandler} to be notified when the object's ready-state changes.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#handler-xhr-onreadystatechange"
   * >http://www.w3.org/TR/XMLHttpRequest/#handler-xhr-onreadystatechange</a>.
   *
   * <p>
   *
   * <p>Note: Applications <em>must</em> call {@link #clearOnReadyStateChange()} when they no longer
   * need this object, to ensure that it is cleaned up properly. Failure to do so will result in
   * memory leaks on some browsers.
   *
   * @param handler the handler to be called when the ready state changes
   * @see #clearOnReadyStateChange()
   */
  @JsOverlay
  public final void setOnReadyStateChange(ReadyStateChangeHandler handler) {
    onreadystatechange = () -> handler.onReadyStateChange(this);
  }

  /**
   * Sets a request header.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-setrequestheader-method"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-setrequestheader-method</a>.
   *
   * @param header the header to be set
   * @param value the header's value
   */
  public final native void setRequestHeader(String header, String value);

  /**
   * Sets withCredentials attribute.
   *
   * <p>See <a href="http://www.w3.org/TR/XMLHttpRequest/#the-withcredentials-attribute"
   * >http://www.w3.org/TR/XMLHttpRequest/#the-withcredentials-attribute</a>.
   *
   * @param withCredentials whether to include credentials in XHR
   */
  @JsProperty(name = "withCredentials")
  public final native void setWithCredentials(boolean withCredentials);

  /**
   * A String specifying the MIME type to use instead of the one specified by the server. If the
   * server doesn't specify a type, XMLHttpRequest assumes "text/xml"
   *
   * <p>See <a
   * href="https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/overrideMimeType"
   * >https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/overrideMimeType</a>.
   *
   * @param mimeType A DOMString specifying the MIME type to use instead of the one specified by the
   *     server.
   * @return
   */
  public final native String overrideMimeType(String mimeType);

  /** The type of response expected from the XHR. */
  public enum ResponseType {
    /**
     * The default response type -- use {@link XMLHttpRequest#getResponseText()} for the return
     * value.
     */
    Default(""),

    /**
     * The default response type -- use {@link XMLHttpRequest#getResponseArrayBuffer()} for the
     * return value. This value may only be used if {@link
     * org.gwtproject.typedarrays.shared.TypedArrays#isSupported()} returns true.
     */
    ArrayBuffer("arraybuffer");

    // not implemented yet
    /*
    Blob("blob"),

    Document("document"),

    Text("text");
    */

    private final String responseTypeString;

    ResponseType(String responseTypeString) {
      this.responseTypeString = responseTypeString;
    }

    public String getResponseTypeString() {
      return responseTypeString;
    }
  }

  /** A callback function to map the Javascript function */
  @JsFunction
  public interface Function {

    void call();
  }
}
