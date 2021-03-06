/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

/*
 * This source code implements specifications defined by the Java
 * Community Process. In order to remain compliant with the specification
 * DO NOT add / change / or delete method signatures!
 */

package javax.portlet;

/**
 * <span class="changed_modified_3_0">The</span> 
 * <CODE>StateAwareResponse</CODE> represents a response that can modify
 * state information or send events.<BR>
 * It extends the PortletResponse interface.
 * 
 * @since 2.0
 * @see PortletResponse
 */
public interface StateAwareResponse extends PortletResponse {

	/**
     * Sets the window state of a portlet to the given window state.
     * <p>
     * Possible values are the standard window states and any custom window
     * states supported by the portal and the portlet. Standard window states
     * are:
     * <ul>
     * <li>MINIMIZED
     * <li>NORMAL
     * <li>MAXIMIZED
     * </ul>
     * 
     * @param windowState
     *            the new portlet window state
     * 
     * @exception WindowStateException
     *                if the portlet cannot switch to the specified window
     *                state. To avoid this exception the portlet can check the
     *                allowed window states with
     *                <code>Request.isWindowStateAllowed()</code>.
     * @exception java.lang.IllegalStateException
     *                if the method is invoked after <code>sendRedirect</code>
     *                has been called.
     * 
     * @see WindowState
     */

	public void setWindowState(WindowState windowState)
			throws WindowStateException;

	/**
     * Sets the portlet mode of a portlet to the given portlet mode.
     * <p>
     * Possible values are the standard portlet modes and any custom portlet
     * modes supported by the portal and the portlet. Portlets must declare in
     * the deployment descriptor the portlet modes they support for each markup
     * type. Standard portlet modes are:
     * <ul>
     * <li>EDIT
     * <li>HELP
     * <li>VIEW
     * </ul>
     * <p>
     * Note: The portlet may still be called in a different window state in the
     * next render call, depending on the portlet container / portal.
     * 
     * @param portletMode
     *            the new portlet mode
     * 
     * @exception PortletModeException
     *                if the portlet cannot switch to this portlet mode, because
     *                the portlet or portal does not support it for this markup,
     *                or the current user is not allowed to switch to this
     *                portlet mode. To avoid this exception the portlet can
     *                check the allowed portlet modes with
     *                <code>Request.isPortletModeAllowed()</code>.
     * @exception java.lang.IllegalStateException
     *                if the method is invoked after <code>sendRedirect</code>
     *                has been called.
     */

	public void setPortletMode(PortletMode portletMode)
			throws PortletModeException;

	/**
    * <span class="changed_modified_3_0">Sets</span> 
	 * a parameter map for the render request.
	 * <div class="changed_deleted_3_0">
    * <p>
    * These parameters will be accessible in all sub-sequent render calls via
    * the <code>PortletRequest.getParameter</code> call until a new request
    * is targeted to the portlet.
    * <p>
    * The given parameters do not need to be encoded prior to calling this
    * method.
    * <p>
    * The portlet should not modify the map any further after calling
    * this method.
    * </div> 
    * <div class="changed_added_3_0"> 
	 * <p>
	 * This method can be used to set both public and private render parameters. 
	 * <p>
	 * These parameters will be accessible in all subsequent render calls via the
	 * PortletRequest.getParameter call until a new request is targeted to the portlet.
	 * <p>
	 * Any previously set private render parameter that is not contained in the new map
	 * is removed. However, public render parameters cannot be removed by excluding
	 * them from the map. Public render parameters that are not included in the map
	 * remain unchanged.
	 * <p>
	 * The given parameters do not need to be encoded prior to calling this method.
	 * <p>
	 * The portlet should not modify the map any further after calling this method.
	 * </div>
	 * 
	 * @param parameters
    * <span class="changed_modified_3_0">
	 * Map containing parameter names for the render phase as keys and
	 * parameter values as map values. The keys in the parameter map must be of type
	 * String and may not be null or the empty string (""). The values in the parameter
	 * map must be of type String array (<code>String[]</code>). 
	 * Neither the values array nor any of
	 * its elements may be null; however, the empty string ("") is allowed.
	 * </span>
	 * 
	 * @exception java.lang.IllegalArgumentException
    * <span class="changed_modified_3_0">
	 *                if parameters is <code>null</code>, if any of the
	 *                keys in the Map are <code>null</code>, if any of
	 *                the keys is not a String, if any of the values is not a
	 *                String array, or if any of the String array elements
	 *                are null. 
    * </span>
	 * @exception java.lang.IllegalStateException
	 *                if the method is invoked after <code>sendRedirect</code>
	 *                has been called.
	 */

	public void setRenderParameters(java.util.Map<String, String[]> parameters);

	/**
     * <span class="changed_modified_3_0">Sets</span> 
     * a String parameter for the render request.
     * <p>
     * <span class="changed_modified_3_0"> 
     * These parameters will be accessible in all subsequent render calls 
     * until an action or render request is
     * targeted to the portlet.
     * </span>
     * <p>
     * This method replaces all parameters with the given key.
     * <p>
     * The given parameter do not need to be encoded prior to calling this
     * method.
     * <p>
     * <span class="changed_added_3_0"> 
     * A parameter value of <code>null</code> indicates that this
     * parameter should be removed. 
     * However, an empty string value ("") is allowed.
     * </span>
     * 
     * @param key
     *            key of the render parameter
     * @param value
     *            value of the render parameter
     * 
     * @exception java.lang.IllegalArgumentException
     *                if key is <code>null</code>;
     * <span class="changed_added_3_0"> 
     *                if an attempt is made to set a public render parameter to <code>null</code>.
     * </span>                 
     * @exception java.lang.IllegalStateException
     *                if the method is invoked after <code>sendRedirect</code>
     *                has been called.
     */

	public void setRenderParameter(String key, String value);

	/**
     * <span class="changed_modified_3_0">Sets
     * a multi-valued String</span> parameter for the render request.
     * <p>
     * <span class="changed_added_3_0"> 
     * These parameters will be accessible in all subsequent render calls 
     * until an action or render request is
     * targeted to the portlet.
     * </span>
     * <p>
     * This method replaces all parameter values with the given key.
     * <p>
     * The given parameter do not need to be encoded prior to calling this
     * method.
     * <p>
     * <span class="changed_added_3_0"> 
     * A values parameter of <code>null</code> indicates that this
     * parameter should be removed. 
     * </span>
     * <p>
     * <span class="changed_added_3_0"> 
     * If the values parameter is not null, no element of the values array may be null. 
     * However, an empty string value ("") is allowed.
     * </span>
     * 
     * @param key
     *            key of the render parameter
     * @param values
     *            values of the render parameter
     * 
     * @exception java.lang.IllegalArgumentException
     *                if name is <code>null</code>; 
     * <span class="changed_added_3_0"> 
     *                if an element of the values array is <code>null</code>; 
     *                if an attempt is made to set a public render parameter to <code>null</code>. 
     * </span>
     * @exception java.lang.IllegalStateException
     *                if the method is invoked after <code>sendRedirect</code>
     *                has been called.
     */

	public void setRenderParameter(String key, String... values);

	/**
     * Publishes an Event with the given payload.
     * <p>
     * The object type of the value must be compliant with the specified event
     * type in the portlet deployment descriptor.
     * <p>
     * The value must have a valid JAXB binding and be serializable.
     * 
     * @param name
     *            the event name to publish, must not be <code>null</code>
     * @param value
     *            the value of this event, must have a valid JAXB binding and 
     *            be serializable, or <code>null</code>.
     * 
     * @exception java.lang.IllegalArgumentException
     *                if name is <code>null</code>, the value is not
     *                serializable, the value does not have a valid JAXB binding, the
     *                object type of the value is not the same as specified in
     *                the portlet deployment descriptor for this event name.
     * @since 2.0
     */
	public void setEvent(javax.xml.namespace.QName name, java.io.Serializable value);

	/**
     * Publishes an Event with the given payload in the default namespace.
     * <p>
     * The name is treated as local part of the event QName and the namespace
     * is either taken from the <code>default-event-namespace</code> element
     * in the portlet deployment descriptor, or if this element is not provided
     * the XML default namespace XMLConstants.NULL_NS_URI is used.
     * <p>
     * The object type of the value must be compliant with the specified event
     * type in the portlet deployment descriptor.
     * <p>
     * The value must have a valid JAXB binding and be serializable.
     * 
     * @param name
     *            the local part of the event name to publish, must not be <code>null</code>
     * @param value
     *            the value of this event, must have a valid JAXB binding and 
     *            be serializable, or <code>null</code>.
     * 
     * @exception java.lang.IllegalArgumentException
     *                if name is <code>null</code>, the value is not
     *                serializable, the value does not have a valid JAXB binding, the
     *                object type of the value is not the same as specified in
     *                the portlet deployment descriptor for this event name.
     * @since 2.0
     */
	public void setEvent(String name, java.io.Serializable value);


	/**
     * <span class="changed_modified_3_0">Returns</span> a <code>Map</code> of the render parameters currently set on
     * this response.
     * <p>
     * The values in the returned <code>Map</code> are of type String array (<code>String[]</code>).
     * <p class="changed_added_3_0">
     * The contents of the returned map are immutable in the sense that modifying the map does not directly 
     * affect the render parameters. In order to set the parameters using the modified map, 
     * the {@link StateAwareResponse#setRenderParameters(Map)} method must be used.
     * </p>
     * <p>
     * If no parameters exist this method returns an empty <code>Map</code>.
     * 
     * @since 2.0
     * 
     * @return <code>Map</code> containing render parameter names as keys and
     *         parameter values as map values, or an empty <code>Map</code> if
     *         no parameters exist. The keys in the parameter map are of type
     *         String. The values in the parameter map are of type String array (<code>String[]</code>).
     */

	public java.util.Map<String, String[]> getRenderParameterMap();

	/**
     * Returns the currently set portlet mode on this reponse.
     * 
     * @since 2.0
     * 
     * @return the portlet mode, or <code>null</code> if none is set
     */

	public PortletMode getPortletMode();

	/**
     * Returns the currently set window state on this response.
     * 
     * @since 2.0
     * 
     * @return the window state, or <code>null</code> if none is set
     */

	public WindowState getWindowState();


	/**
	 * Removes the specified public render parameter.
	 * The name must reference a public render parameter defined
	 * in the portlet deployment descriptor under the
	 * <code>public-render-parameter</code> element with the
	 * <code>identifier</code> mapping to the parameter name.
	 * 
	 * @param name			a <code>String</code> specifying 
	 *					the name of the public render parameter to be removed
	 *
	 * @exception  java.lang.IllegalArgumentException 
	 *                            if name is <code>null</code>.
	 * @since 2.0
	 */
	public void removePublicRenderParameter(String name);
}
