package aulaso20161.trabalhofinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import aulaso20161.trabalhofinal.R;
import aulaso20161.trabalhofinal.model.EntregaModel;


/**
 * Created by DevMaker on 3/16/16.
 */
public class EntregasAdapter extends BaseAdapter {

    private final Context mContext;
    private List<EntregaModel> entregas;


    public EntregasAdapter(Context context, List<EntregaModel> entregas) {
        this.mContext = context;
        this.entregas = entregas;

    }

    @Override
    public int getCount() {
        return entregas.size();
    }

    @Override
    public EntregaModel getItem(int position) {
        return entregas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        final EntregaModel entrega = entregas.get(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_entregas, parent, false);

            holder = new ViewHolder();
            holder.endereco = (TextView) convertView.findViewById(R.id.txtEnd);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.endereco.setText(entrega.getEndereco());


        return convertView;
    }

    class ViewHolder {
        public TextView endereco;

    }

}
