import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

const BASE_URL = 'http://localhost:8080';

@Injectable()
export class FileService {
    constructor(private http: HttpClient) { }

    getAllFiles(): Observable<string[]> {
        return this.http.get<string[]>(BASE_URL + '/api/rest/files');
    }

    downloadFile(file): Observable<any> {
        return this.http.get<any>(BASE_URL + '/api/rest/files/file/' + file);
    }
}
