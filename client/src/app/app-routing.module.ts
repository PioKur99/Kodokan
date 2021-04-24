import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PageNotFoundComponent } from './error-routing/not-found/not-found.component';
import { UncaughtErrorComponent } from './error-routing/error/uncaught-error.component';
import { ErrorRoutingModule } from './error-routing/error-routing.module';
import { ReceptionistPanelComponent } from './receptionist-panel/receptionist-panel.component';

export const routes: Routes = [
  { path: '', redirectTo: 'client-panel1/packages1', pathMatch: 'full' },
  { path: 'error', component: UncaughtErrorComponent },
  { path: 'client-panel', loadChildren: () => import('./client-panel/client-panel.module').then(m => m.ClientPanelModule) },
  { path: 'receptionist-panel', component: ReceptionistPanelComponent, data: { text: 'receptionist-panel' } },
  { path: 'client-panel1', loadChildren: () => import('./client-panel1/client-panel1.module').then(m => m.ClientPanel1Module) },
  { path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule) },
  { path: '**', component: PageNotFoundComponent } // must always be last
];

@NgModule({
  imports: [RouterModule.forRoot(routes), ErrorRoutingModule],
  exports: [RouterModule, ErrorRoutingModule]
})
export class AppRoutingModule {
}
