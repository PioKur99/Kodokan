import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientPanelRoutingModule } from './client-panel-routing.module';
import { ClientPanelComponent } from './client-panel.component';
import { PackagesComponent } from './packages/packages.component';
import { IgxCardModule, IgxButtonModule, IgxNavbarModule, IgxToggleModule, IgxIconModule, IgxNavigationDrawerModule } from 'igniteui-angular';
import { FormsModule } from '@angular/forms';
import { PersonalDataComponent } from './personal-data/personal-data.component';

@NgModule({
  declarations: [ClientPanelComponent, PackagesComponent, PersonalDataComponent],
  imports: [
    CommonModule,
    ClientPanelRoutingModule,
    IgxCardModule,
    IgxButtonModule,
    FormsModule,
    IgxNavbarModule,
    IgxToggleModule,
    IgxIconModule,
    IgxNavigationDrawerModule
  ]
})
export class ClientPanelModule {
}
