import { BrowserModule, HammerModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PackagesComponent } from './packages/packages.component';
import { IgxCardModule, IgxButtonModule, IgxNavbarModule, IgxToggleModule, IgxIconModule, IgxNavigationDrawerModule } from 'igniteui-angular';
import { FormsModule } from '@angular/forms';
import { PersonalDataComponent } from './personal-data/personal-data.component';

@NgModule({
  declarations: [
    AppComponent,
    PackagesComponent,
    PersonalDataComponent
  ],
  imports: [
    BrowserModule,
    HammerModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    IgxCardModule,
    IgxButtonModule,
    FormsModule,
    IgxNavbarModule,
    IgxToggleModule,
    IgxIconModule,
    IgxNavigationDrawerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
