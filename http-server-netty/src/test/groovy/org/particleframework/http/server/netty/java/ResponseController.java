/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.http.server.netty.java;

import org.particleframework.http.*;
import org.particleframework.http.annotation.Controller;
import org.particleframework.http.annotation.Get;

/**
 * @author Graeme Rocher
 * @since 1.0
 */
@Controller("/java/response")
public class ResponseController {

    @Get
    public HttpResponse disallow() {
        return HttpResponse.notAllowed(HttpMethod.DELETE);
    }

    @Get
    public HttpResponse accepted() {
        return HttpResponse.accepted();
    }

    @Get
    public HttpResponse createdUri() {
        return HttpResponse.created(HttpResponse.uri("http://test.com"));
    }

    @Get
    public HttpResponse createdBody() {
        return HttpResponse.created(new Foo("blah", 10));
    }

    @Get
    public HttpResponse ok() {
        return HttpResponse.ok();
    }

    @Get(produces = MediaType.TEXT_PLAIN)
    public HttpResponse okWithBody() {
        return HttpResponse.ok("some text");
    }

    @Get
    public HttpResponse<Foo> okWithBodyObject() {
        return HttpResponse.ok(new Foo("blah", 10))
                           .headers((headers)->
                                headers.contentType(MediaType.APPLICATION_JSON_TYPE)
                           );
    }

    @Get
    public HttpMessage status() {
        return HttpResponse.status(HttpStatus.MOVED_PERMANENTLY);

    }
}
