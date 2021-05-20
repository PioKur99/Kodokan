import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageNotFoundComponent } from './error-routing/not-found/not-found.component';
import { UncaughtErrorComponent } from './error-routing/error/uncaught-error.component';
import { ErrorRoutingModule } from './error-routing/error-routing.module';

export const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'error', component: UncaughtErrorComponent },
  { path: 'receptionist-panel', loadChildren: () => import('./receptionist-panel/receptionist-panel.module').then(m => m.ReceptionistPanelModule) },
  { path: 'customer-panel', loadChildren: () => import('./customer-panel/customer-panel.module').then(m => m.CustomerPanelModule) },
  { path: '**', component: PageNotFoundComponent } // must always be last
];


@NgModule({
  imports: [RouterModule.forRoot(routes), 
    ErrorRoutingModule],
  exports: [RouterModule, ErrorRoutingModule]
})
export class AppRoutingModule {
}
