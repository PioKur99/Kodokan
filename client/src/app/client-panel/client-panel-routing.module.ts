import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientPanelComponent } from './client-panel.component';
import { PackagesComponent } from './packages/packages.component';
import { PersonalDataComponent } from './personal-data/personal-data.component';

const routes: Routes = [{ path: '', component: ClientPanelComponent, children: [{ path: '', redirectTo: 'packages', pathMatch: 'full' }, { path: 'packages', component: PackagesComponent, data: { text: 'packages' } }, { path: 'personal-data', component: PersonalDataComponent, data: { text: 'personal-data' } }] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientPanelRoutingModule {
}
