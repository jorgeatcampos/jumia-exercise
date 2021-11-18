import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable()
export class AppService {
	private readonly apiUrl = `${environment.apiUrl}/phoneNumber`;

	constructor (private http: HttpClient) {
	}

	getCountries(): Observable<any> {
		return this.http.get<string[]>(`${this.apiUrl}/getCountries`).pipe(
            tap(results => { 
                results.sort()
            })
        )
	}

	getPhoneNumbers(country?: string, isValid?: boolean, numberOfRecords?: number, pageIndex?: number): Observable<any> {
		let httpParams = new HttpParams();
		
		httpParams = country ? httpParams.append('country', country.toString()) : httpParams;
		//httpParams = isValid ? httpParams.append('state', isValid) : httpParams;

        httpParams = numberOfRecords ? httpParams.append('numberOfRecords', numberOfRecords.toString()) : httpParams;
        httpParams = pageIndex ? httpParams.append('pageIndex', pageIndex.toString()) : httpParams;
        httpParams = httpParams.append('pageIndex', 0);

		return this.http.get<any>(`${this.apiUrl}/getList`, {params: httpParams});
	}
}