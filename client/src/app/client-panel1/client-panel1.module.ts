import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientPanel1RoutingModule } from './client-panel1-routing.module';
import { ClientPanel1Component } from './client-panel1.component';
import { Packages1Component } from './packages1/packages1.component';
import { IgxCardModule, IgxButtonModule, IgxNavbarModule, IgxToggleModule, IgxIconModule, IgxNavigationDrawerModule } from 'igniteui-angular';
import { FormsModule } from '@angular/forms';
import { PersonalData1Component } from './personal-data1/personal-data1.component';

@NgModule({
  declarations: [ClientPanel1Component, Packages1Component, PersonalData1Component],
  imports: [
    CommonModule,
    ClientPanel1RoutingModule,
    IgxCardModule,
    IgxButtonModule,
    FormsModule,
    IgxNavbarModule,
    IgxToggleModule,
    IgxIconModule,
    IgxNavigationDrawerModule
  ]
})
export class ClientPanel1Module {
}
