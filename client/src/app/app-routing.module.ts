import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageNotFoundComponent } from './error-routing/not-found/not-found.component';
import { UncaughtErrorComponent } from './error-routing/error/uncaught-error.component';
import { ErrorRoutingModule } from './error-routing/error-routing.module';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  { path: 'error', component: UncaughtErrorComponent },
  { path: 'receptionist-panel', loadChildren: () => import('./receptionist-panel/receptionist-panel.module').then(m => m.ReceptionistPanelModule) },
  { path: 'customer-panel', loadChildren: () => import('./customer-panel/customer-panel.module').then(m => m.CustomerPanelModule) },
  { path: 'member-card', loadChildren: () => import('./member-card/member-card.module').then(m => m.MemberCardModule) },
  { path: '**', component: PageNotFoundComponent } // must always be last
];


@NgModule({
  imports: [RouterModule.forRoot(routes), 
    ErrorRoutingModule],
  exports: [RouterModule, ErrorRoutingModule]
})
export class AppRoutingModule {
}
