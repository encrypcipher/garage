import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { DataTablesModule } from 'angular-datatables';
import { AppComponent } from './app.component';
import { CarDiaplayComponent } from './components/car-diaplay/car-diaplay.component';
import { CarDetailsComponent } from './components/car-details/car-details.component';
import { ShoopingCartComponent } from './components/shooping-cart/shooping-cart.component';
import { CarBoardComponent } from './components/car-board/car-board.component';

const appRoutes: Routes = [
  { path: 'board', component: CarBoardComponent },
  { path: 'diaplay', component: CarDiaplayComponent },
  { path: 'details', component: CarDetailsComponent },
  { path: '**', redirectTo: '/board' },
  { path: '', redirectTo: '/board', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    CarDiaplayComponent,
    CarDetailsComponent,
    ShoopingCartComponent,
    CarBoardComponent,
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes
    ),
    HttpClientModule,
    BrowserModule,
    DataTablesModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
