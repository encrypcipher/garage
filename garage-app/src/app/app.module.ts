import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DataTablesModule } from 'angular-datatables';
import { AppComponent } from './app.component';
import { CarDiaplayComponent } from './car-diaplay/car-diaplay.component';
import { CarDetailsComponent } from './car-details/car-details.component';

const appRoutes: Routes = [
  { path: 'diaplay', component: CarDiaplayComponent },
  { path: 'details', component: CarDetailsComponent },
  { path: '**', redirectTo: '/diaplay' },
  { path: '', redirectTo: '/diaplay', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    CarDiaplayComponent,
    CarDetailsComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes
    ),
    BrowserModule,
    DataTablesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
