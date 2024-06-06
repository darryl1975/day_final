import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Bedroom1Component } from './bedroom1/bedroom1.component';
import { Bedrooom2Component } from './bedrooom2/bedrooom2.component';

@NgModule({
  declarations: [
    AppComponent,
    Bedroom1Component,
    Bedrooom2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
