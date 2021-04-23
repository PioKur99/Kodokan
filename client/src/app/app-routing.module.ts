import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageNotFoundComponent } from './error-routing/not-found/not-found.component';
import { UncaughtErrorComponent } from './error-routing/error/uncaught-error.component';
import { ErrorRoutingModule } from './error-routing/error-routing.module';
import { PackagesComponent } from './packages/packages.component';
import { PersonalDataComponent } from './personal-data/personal-data.component';

export const routes: Routes = [
  { path: '', redirectTo: 'packages', pathMatch: 'full' },
  { path: 'error', component: UncaughtErrorComponent },
  { path: 'packages', component: PackagesComponent, data: { text: 'packages' } },
  { path: 'personal-data', component: PersonalDataComponent, data: { text: 'personal-data' } },
  { path: '**', component: PageNotFoundComponent } // must always be last
];

@NgModule({
  imports: [RouterModule.forRoot(routes), ErrorRoutingModule],
  exports: [RouterModule, ErrorRoutingModule]
})
export class AppRoutingModule {
}
