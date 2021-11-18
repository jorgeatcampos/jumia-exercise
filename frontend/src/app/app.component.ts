import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { merge, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { AppService } from './service/service';
import { PhoneNumber } from './model/phone-number';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
})
export class AppComponent implements AfterViewInit, OnInit {
    title = 'Jumia Exercise';

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;

    displayedColumns: string[] = [
        'id',
        'name',
        'country',
        'countryCode',
        'phone',
        'valid',
    ];
    data: PhoneNumber[] = [];

    countries = [];

    resultsLength = 0;
    isLoadingResults = true;
    filters = { country: '', state: true };

    constructor(private appService: AppService) {}

    ngOnInit() {
        this.getCountries();
    }

    ngAfterViewInit() {
        this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

        merge(this.sort.sortChange, this.paginator.page)
            .pipe(
                startWith({}),
                switchMap(() => {
                    this.isLoadingResults = true;
                    return this.appService!.getPhoneNumbers(
                        this.filters.country,
                        this.filters.state,
                        this.paginator.pageSize,
                        this.paginator.pageIndex
                    );
                }),
                map((response) => {
                    this.isLoadingResults = false;
                    this.resultsLength = response.totalRecords;

                    return response.phoneNumberDTOList;
                }),
                catchError(() => {
                    this.isLoadingResults = false;

                    return observableOf([]);
                })
            )
            .subscribe((data) => (this.data = data));
    }

    onSearchByCountry(value: string): void {
        this.filters.country = value;
        this.getPhoneNumbers();
    }

    onSearchByState(value: boolean): void {
        this.filters.state = value;
        this.getPhoneNumbers();
    }

    private getPhoneNumbers(): void {
        this.paginator.pageIndex = 0;

        this.appService!.getPhoneNumbers(
            this.filters.country,
            this.filters.state,
            this.paginator.pageSize,
            0
        ).subscribe((response) => {
            this.data = response.phoneNumberDTOList;
            this.resultsLength = !this.data.length ? 0 : response.totalRecords;
        });
    }

    private getCountries(): void {
        this.appService
            .getCountries()
            .subscribe((response) => (this.countries = response));
    }
}
