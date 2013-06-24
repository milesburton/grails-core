/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.grails.web.binding.bindingsource

import groovy.transform.CompileStatic

import javax.servlet.http.HttpServletRequest

import org.apache.commons.io.IOUtils
import org.codehaus.groovy.grails.web.mime.MimeType
import org.grails.databinding.DataBindingSource

@CompileStatic
abstract class AbstractRequestBodyDataBindingSourceHelper implements DataBindingSourceHelper {

    @Override
    public DataBindingSource createDataBindingSource(MimeType mimeType, Object bindingTarget, Object bindingSource) {
        def req = (HttpServletRequest)bindingSource
        def is = req.getInputStream()
        def reader = new InputStreamReader(is)
        def bodyText = IOUtils.toString(reader)
        convertStringToBindingSource(bodyText)
    }

    protected abstract DataBindingSource convertStringToBindingSource(String bodyText)
}