import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, finalize } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { JwtManagerService } from './jwt-manager.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor
{
  serverUrl = environment.serverUrl;

  constructor(private jwt: JwtManagerService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>
  {
    const token = this.jwt.getJwt();

    if(token)
    {
      request = request.clone(
        {
          headers: request.headers.set('Authorization', `Bearer ${token}`)
        }
      );
    }

    if(!request.headers.has('Content-Type'))
    {
      request = request.clone(
        {
          headers: request.headers.set('Content-Type', 'application/json; charset=utf-8')
        }
      );
    }

    request = request.clone(
      {
        headers: request.headers.set('Accept', 'application/json')
      }
    );

    const newUrl = 
    {
      url: this.serverUrl + request.url
    }
    request = Object.assign(request, newUrl);
    const newUrlWithParans =
    {
      urlWithParams: this.serverUrl + request.urlWithParams
    }
    request = Object.assign(request, newUrlWithParans);

    return next.handle(request).pipe(
      map((event: HttpEvent<any>) =>
      {
        return event;
      }),
      finalize(() => {})
    );
  }
}
